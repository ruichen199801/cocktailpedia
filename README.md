# CIT594 HW8 Final Project - Team Algoholic

## Project Name: CocktailPedia

![Blue Margarita](./margarita.jpg)

## Team Members

|         Name         |          Email          |
|:--------------------:|:-----------------------:|
|    Ruichen Zhang     | ruichenz@seas.upenn.edu |
|      Rui Zhang       | ruizhan@seas.upenn.edu  |
|     Wenrui Zhang     |  wrzhang@sas.upenn.edu  |
|       Yuxin Hu       | yuxinhu@seas.upenn.edu |

## File Structure

```
CocktailPedia (parent directory)
├── datasets                           // cleaned cocktail dataset
│   └── recipe_dataset.csv
├── src
│   ├── ICocktailRecommender.java      // interface
│   ├── CocktailRecommender.java       // implementation class
│   ├── CocktailRecommenderTest.java   // test class 
│   ├── RecommenderApplication.java    // boot file
│   ├── Cocktail.java                  // cocktail recipe class
│   ├── Recipe.java                    // customized recipe class
│   └── Style.java                     // preparation style enum class
└── users                              // store users' customized recipes
    └── test.txt
```

## Introduction

CocktailPedia is a Java-based cocktail recommender system. It uses cocktail datasets on the web as the data source to provide recipe lookup and recommendations for users. It also allows users to customize their own recipes, and export them in a text file format.

## Project Features

The project provides the following features:

1. Recipe lookup
  * A user can request the recipe of a cocktail by the cocktail name. The recipe contains information such as drink name, category, glassware, ingredients, taste and preparation instructions.

2. Recommendation
  * If a user is new or does not have a specific preference, the user will be recommended one of the classic recipes by default.

  * If a user has a taste preference, the user will be recommended one of the cocktail recipes with the user’s favorite taste.

  * After a recipe is recommended to a user, we increase the popularity point of the recipe, so that the most popular cocktails will be recommended first instead of randomly chosen.

  * Based on these basic building blocks of recommendations by a single feature, we will design a more complex recommendation algorithm based on multiple factors, such as both popularity and user preference.

3. Recipe customization
  * The user can select from a few customization options to get a customized recipe in a text file format. The options include ingredients, preparation style and taste.  

The features mentioned above are achieved via interactions with the user in the console. After the program is manually run by a user, it will accept user input, execute corresponding functions, and print the recipes and helper information in the console based on different user options. Users can also locate the recipe files they created under the directory of their username.

## Languages and Data Structures

This project is Java-based, but we will use very limited Python to assist us in cleaning the dataset. Our project will involve data structures such as maps, arrays, heaps. For example, we will use a hash table to store cocktail recipes imported from the dataset, and a sorted map to keep track of the popularity of cocktail recipes.

## References

1. https://www.kaggle.com/datasets/ai-first/cocktail-ingredients
