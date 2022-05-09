# CIT 594 Final Project: CocktailPedia 

## Team Algoholic

<img src="./margarita.jpg" alt="Blue Margarita" width="400"/>

## Team Members

|         Name         |          Email          |
|:--------------------:|:-----------------------:|
|    Ruichen Zhang     | ruichenz@seas.upenn.edu |
|      Rui Zhang       | ruizhan@seas.upenn.edu  |
|     Wenrui Zhang     |  wrzhang@sas.upenn.edu  |
|       Yuxin Hu       | yuxinhu@seas.upenn.edu |

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

3. Discount combo
  * This is also part of the recommendation feature, which is to recommend cocktail combos with the most discount to users. The user can choose two drinks from a list of cocktails that are currently on discount, and our recommender system will calculate the best discount of cocktail combo sets with these two drinks included, and return the cocktail combo.

4. Recipe customization
  * The user can select from a few customization options to get a customized recipe in a text file format. The options include ingredients, preparation style and taste.  

The features mentioned above are achieved via interactions with the user in the console. After the program is manually run by a user, it will accept user input, execute corresponding functions, and print the recipes and helper information in the console based on different user options. Users can also locate the recipe files they created under the directory of their username.

## Languages, Data Structures and Algorithms

This project is Java-based, but we will use very limited Python to assist us in cleaning the dataset. Our project involves the use of data structures such as graphs, trees, maps, arrays. 

For example, we will use a hash table to store cocktail recipes imported from the dataset, and a sorted map to keep track of the popularity of cocktail recipes. We will also maintain a Trie tree to enhance in cocktail lookup, so that even if users make a typo or enter incomplete words, they can still get the correct result. To implement the discount combo feature, we will maintain a graph to store the drinks on discount as vertices, and discount ratio as weighted edges.

In terms of algorithms, we will run breadth-first search (BFS) when searching for matching recipes. We will recommend combos with the best discount to users by running shortest path algorithms, namely Dijkstra's Algorithm, to find the shortest sum paths.


## File Structure

```
CocktailPedia (parent directory)
├── datasets                           // store cleaned dataset
│   └── cocktail_df_cleaned.txt
├── src
│   ├── ICocktailRecommender.java      // interface    
│   ├── CocktailRecommender.java       // implemenation class
│   ├── CocktailRecommenderTest.java   
│   ├── Cocktail.java                  // dataset cocktail recipe
│   ├── CocktailTest.java                  
│   ├── Recipe.java                    // customized cocktail recipe                
│   ├── RecipeTest.java    
│   ├── Trie.java                      // trie 
│   ├── TrieTest.java     
│   ├── GraphM.java                    // graph
│   ├── GraphMTest.java                              
│   └── RecommenderApplication.java    // main boot file
└── users                              // store users' customized recipes
    └── testUser
      └── test.txt
```

## Git Instructions

1. Run `git clone git@github.com:upenn-cit594/hw-8---project-team-algoholic.git` to clone the project to your local machine.
2. `cd` into the repo you just created, run `git checkout -b [your name]` to create your own branch. Next time you want to switch to a different branch, make sure you've committed all your local changes first, then run `git checkout [branch name]`.
3. After you have made some code changes on your branch, run `git add .`, `git commit -m "your commit message"`, `git push` to commit your change to your remote branch on GitHub. Note that you need to run `git push --set-upstream origin [your branch name]` **the first time** after you created your local branch. Only push codes to GitHub if you make sure that the app is not broken after your latest code change.
4. Regularly push your code to GitHub, and make pull requests from main branch to fetch latest changes.
5. Remember to manaully create your `.gitignore` file to ignore metadata and output files generated by local IDEs.

## References

1. https://www.kaggle.com/datasets/ai-first/cocktail-ingredients
