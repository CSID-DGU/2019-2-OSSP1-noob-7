package DGU.OSSP.fall2019.PersonalTrainer.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import DGU.OSSP.fall2019.PersonalTrainer.Classes.APIHandler;
import DGU.OSSP.fall2019.PersonalTrainer.Classes.Ingredient;
import DGU.OSSP.fall2019.PersonalTrainer.Classes.Ingredients;
import DGU.OSSP.fall2019.PersonalTrainer.Classes.Recipe;
import DGU.OSSP.fall2019.PersonalTrainer.Classes.SQLiteDBManager;
import DGU.OSSP.fall2019.PersonalTrainer.Classes.SQLiteIngredient;
import DGU.OSSP.fall2019.PersonalTrainer.Classes.SQLiteMeal;
import DGU.OSSP.fall2019.PersonalTrainer.Classes.UserPreferences;
import DGU.OSSP.fall2019.PersonalTrainer.Fragments.FavoritesFragment;
import DGU.OSSP.fall2019.PersonalTrainer.Fragments.FiltersFragment;
import DGU.OSSP.fall2019.PersonalTrainer.Fragments.HomeFragment;
import DGU.OSSP.fall2019.PersonalTrainer.Fragments.MealDetailFragment;
import DGU.OSSP.fall2019.PersonalTrainer.Fragments.PreferencesFragment;
import DGU.OSSP.fall2019.PersonalTrainer.Fragments.SearchFragment;
import DGU.OSSP.fall2019.PersonalTrainer.Fragments.ShoppingListFragment;
import DGU.OSSP.fall2019.DGU.R;

public class MainActivity extends AppCompatActivity
        implements SearchFragment.SearchFragmentListener,
        FiltersFragment.FiltersFragmentListener,
        MealDetailFragment.MadeMealListener,
        MealDetailFragment.ScheduleMealListener,
        ShoppingListFragment.RefreshShoppingList {

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    public static int navItemIndex = 0;

    private static final String TAG_HOME = "home";
    private static final String TAG_SEARCH = "search";
    private static final String TAG_FAVORITES = "favorites";
    private static final String TAG_SHOPPING = "shopping";
    private static final String TAG_PREFERENCES = "preferences";
    private static final String TAG_MEALDETAIL = "mealdetail";
    private static final String TAG_FILTERS = "filters";
    private Fragment fragment;
    public static String CURRENT_TAG = TAG_HOME;

    private FiltersFragment mFiltersFragment;
    private SearchFragment mSearchFragment;

    private boolean shouldLoadHomeFragOnBackPress = false;

    private String searchText = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_nav_drawer);

        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        setUpNavigationView();

        // Occurs when first starting app, don't add fragment to backstack
        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            Fragment fragment = getHomeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            //fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            fragmentTransaction.add(R.id.frame, fragment, CURRENT_TAG);
            fragmentTransaction.commit();
        }
    }

    private void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.navI_home:
                                navItemIndex = 0;
                                CURRENT_TAG = TAG_HOME;
                                break;
                            case R.id.navI_search:
                                navItemIndex = 1;
                                CURRENT_TAG = TAG_SEARCH;
                                break;
                            case R.id.navI_favorites:
                                navItemIndex = 2;
                                CURRENT_TAG = TAG_FAVORITES;
                                break;
                            case R.id.navI_shopping:
                                navItemIndex = 3;
                                CURRENT_TAG = TAG_SHOPPING;
                                break;
                            case R.id.navI_settings:
                                navItemIndex = 4;
                                CURRENT_TAG = TAG_PREFERENCES;
                                break;
                            case R.id.action_logout:
                                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                                startActivity(intent);
                                finish();
                            default:
                                navItemIndex = 0;
                        }

                        loadHomeFragment();

                        return true;
                    }
                });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawer.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void loadHomeFragment() {
        selectNavMenu();

        Fragment fragment = getHomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        drawer.closeDrawers();
    }

    private Fragment getHomeFragment() {
        Fragment frag;
        switch (navItemIndex) {
            case 0:
                frag = getSupportFragmentManager().findFragmentByTag(TAG_HOME);
                if(frag == null) {
                    HomeFragment homeFrag = new HomeFragment();

                    return homeFrag;
                }
                else
                    return frag;
            case 1:
                frag = getSupportFragmentManager().findFragmentByTag(TAG_SEARCH);
                if(frag == null) {
                    searchText = "";
                    return new SearchFragment();
                }
                else
                    return frag;
            case 2:
                frag = getSupportFragmentManager().findFragmentByTag(TAG_FAVORITES);
                if(frag == null)
                    return new FavoritesFragment();
                else
                    return new FavoritesFragment();
            case 3:
                frag = getSupportFragmentManager().findFragmentByTag(TAG_SHOPPING);
                if(frag == null)
                    return new ShoppingListFragment();
                else
                    return frag;
            case 4:
                frag = getSupportFragmentManager().findFragmentByTag(TAG_PREFERENCES);
                if(frag == null)
                    return new PreferencesFragment();
                else
                    return frag;
            default:
                return new HomeFragment();
        }
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        if(CURRENT_TAG.equals(TAG_FILTERS)) {
            Toast a = Toast.makeText(this, "back press current tag filters", Toast.LENGTH_SHORT);
            a.setGravity(Gravity.CENTER,0,0);
            a.show();
            navItemIndex = 1;
            CURRENT_TAG = TAG_SEARCH;
            Fragment frag = (SearchFragment) getSupportFragmentManager().findFragmentByTag(TAG_SEARCH);
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            //fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,  android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.frame, frag, TAG_SEARCH);
            fragmentTransaction.commit();
        }
        else if(CURRENT_TAG.equals(TAG_HOME) && getSupportFragmentManager().getBackStackEntryCount() == 0)
            return;
        else
            super.onBackPressed();
    }

    // Implement listener for Search Fragment
    public void goToFilters(String inputString) {

        searchText = inputString;   // save search string if user inputted one before going to filters
        mFiltersFragment = new FiltersFragment();
        navToFragment(mFiltersFragment, TAG_FILTERS);
    }

    public void addToShoppingList(String bookmarkURL, String mealName) {
        ArrayList<String> ingredientsAsStrings = new ArrayList<>();
        ArrayList<String> bookmarks = new ArrayList<>();
        bookmarks.add(bookmarkURL);

        // grab ingredients from API using bookmarkURL
        ArrayList<Recipe> recipes = new APIHandler().getRecipesFromBookmarks(bookmarks);
        Ingredients ingredients = recipes.get(0).ingredients();
        for (int i = 0; i < ingredients.length(); ++i) {
            Ingredient currentIngredient = ingredients.getIngredientAtIndex(i);
            ingredientsAsStrings.add(currentIngredient.text());
        }
        Set<String> ingredientSet = new HashSet<>();
        ingredientSet.addAll(ingredientsAsStrings);
        ingredientsAsStrings.clear();
        ingredientsAsStrings.addAll(ingredientSet);

        ArrayList<SQLiteIngredient> ingredientsList = new ArrayList<>();

        for(int x = 0; x < ingredientsAsStrings.size(); x++) {
            ingredientsList.add(new SQLiteIngredient(ingredientsAsStrings.get(x), false));
        }
        SQLiteMeal thisMeal = new SQLiteMeal(recipes.get(0).name().replaceAll("[^a-zA-Z\\d\\s]", "_"), ingredientsList);

        SQLiteDBManager dbManager = new SQLiteDBManager(this);
        dbManager.addEntry(thisMeal);
        dbManager.close();
    }

    public void navToFragment(Fragment fragment, String tag) {
        CURRENT_TAG = tag;

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame, fragment, tag);
        if(!tag.equals(TAG_FILTERS)) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    // Implement listener for Filters Fragment
    public void sendFiltersToSearch(UserPreferences filters) {
        Toast.makeText(this, "Search filters saved", Toast.LENGTH_SHORT).show();
        mSearchFragment = (SearchFragment) getSupportFragmentManager().findFragmentByTag(TAG_SEARCH);

        mSearchFragment.applyFiltersToSearch(searchText, filters);
        navToFragment(mSearchFragment, TAG_SEARCH);
    }

    // Implementation of MadeMealListener interface
    public void afterMadeMealClick() {
        HomeFragment homeFrag = (HomeFragment) getSupportFragmentManager().findFragmentByTag(TAG_HOME);
        navToFragment(homeFrag, TAG_HOME);
    }

    // Implementation of ScheduleMealListener interface
    public void showHomeScreenAfterScheduleMeal() {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        HomeFragment homeFrag = new HomeFragment();
        navToFragment(homeFrag, TAG_HOME);
    }

    // Implementation of RefreshShoppingList interface
    public void refreshShoppingListFragment() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment slFragment = fm.findFragmentByTag(TAG_SHOPPING);
        if(slFragment == null)
            slFragment = new ShoppingListFragment();
        fm.beginTransaction().detach(slFragment).attach(slFragment).commit();
        CURRENT_TAG = TAG_SHOPPING;
    }

    @Override
    public void sendToShoppingListFromMealFragment(String bookmarkURL) {
        addToShoppingList(bookmarkURL, "");
    }
}