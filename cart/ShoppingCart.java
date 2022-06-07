package cart;

import java.io.Console;

import java.util.*;

public class ShoppingCart {
    
    public static void main (String []args) {

        System.out.println(" Welcome to your shopping cart");
        List<String> cart = new LinkedList<>();
        Console cons = System.console();
        String input;
        int delIndex;
        boolean stop = false;

        cart.add ("apple");
        cart.add ("orange");
        cart.add ("pear");

        //main loop 
        while (!stop) {
            //indicate next line with >
            input = cons.readLine ("> ");
            //reads back what you input
            System.out.printf("READ: %s\n", input);
            //convert spaces into separate substrings i.e. add apple becomes add and apple
            String[] terms = input.split(" ");
            //identifies that the command is the first word in the array, i.e. in add apple, add is the cmd.
            String cmd = terms[0];

            //converts commands in the console to lower case
            switch(cmd.toLowerCase()) {
                case "add":
                    //identifies the fruit as the second word in the terms array
                    String fruitsStr = terms[1];
                    //identifies comma and splits the terms into separate Strings in the array
                    String fruitsReplaced = fruitsStr.replace(",", " ");
                    //fruits becomes fruitsReplaced and eliminate the space
                    String[] fruits = fruitsReplaced.split(" ");

                    //fruits.length cannot be empty
                    for (int i = 0; i < fruits.length; i++) {
                        boolean found = false;
                        //cart size cannot be empty
                        for (int j=0; j < cart.size(); j++) {
                            //if the added item already exists in the cart, do not add it. break the loop.
                            if (fruits[i].toUpperCase().equals(cart.get(j).toUpperCase())) {
                                //im adding a statement so they know the item has been added
                                System.out.println("the item is already in your list!");
                                found = true;
                                break;
                            }
                        }
                        //if the loop is not found, i can continue and i will add the the word from fruits[i] to my cart
                        if (!found) {
                            cart.add(fruits[i]);
                            //Print a receipt acknowledgint the cart has been added
                            System.out.printf("Added %s to cart\n", fruits[i]);
                        }
                    }
                    break;
                case "list":
                    //In list, check that cart is not empty
                    if(cart.size()> 0) {
                        //as long as the cart size is not empty, print out the number (i+1) and the index of i (ie. 1. Apple)
                        for(int i=0; i< cart.size(); i++) {
                            System.out.printf("%d. %s\n", (i+1), cart.get(i));
                        }
                    } else {
                        //if cart is empty print that it's empty
                        System.out.println("Your cart is empty!");
                    }
                    break;
                case "del":
                    // if my termslength (ie cmd) is less than two words long, that means i only typed del
                    if (terms.length < 2) {
                        //pritn statement 
                        System.out.println("Please provide an index in order to delete");
                    } else {
                        try{
                            //the delete works, now i convert the String (cos cmd) thats indicated into an integer -1 
                            delIndex = Integer.parseInt(terms[1]) -1;
                            //print the index 
                            System.out.println(delIndex);
                            //the delIndex number has to be greater than 0 and the cart size has to be bigger than it
                            if (delIndex >= 0 && delIndex <cart.size()) {
                                //print receipt
                                System.out.printf("Deleted %s from cart \n", cart.get(delIndex));
                                //remove the item
                                cart.remove(delIndex);
                            // if the deleted index number is negative or 0, or larger than the cart size
                            } else {
                                //no item to delete
                                showNoSuchItemToDel ();
                            } 
                        //if the number has a format error like you write a String or sth - ask Kenneth?
                        } catch(NumberFormatException e) {
                            showNoSuchItemToDel();
                        }
                      
                    }
                    break;
                case "end":
                    //command to stop the main loop
                    stop = true;
                    break;
                default:   
                    System.out.println("Invalid Command!!");

            }

         }
         System.out.println("Thank you for shopping with us");
    }
    //create a no such item method -- why at the end. does it matter? - ask kenneth
    private static void showNoSuchItemToDel() {
        System.out.println("No such item to delete");
    }
}
