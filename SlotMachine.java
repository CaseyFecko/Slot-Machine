import javax.swing.*;
import java.awt.Font;

public class SlotMachine
{
    private int[] result;
    private int three;
    private int two;
    private int credits;
    private int orgCredits;
    private int round;
    private int cost;
    private int twoWin;
    private int threeWin;
    private boolean error;
    private boolean added;
    private int totalAdded;
    private boolean stop;

    //icons for the dialog boxes, going down, for when the player gets a two of a kind, three of a kind, no match, and the generic slot machine image
    private ImageIcon twoImage;
    private ImageIcon threeImage;
    private static ImageIcon noImage = new ImageIcon("No.png");
    private static ImageIcon slotImage = new ImageIcon("Slot.png");

    public SlotMachine()
    {
        result = new int[3];
        three = 0;
        two = 0;
        round = 0;
        
        added = false;
        totalAdded=0;
        stop = false;
        // introduces game and sets credits spent per round
        displayRules();
        String costStr=JOptionPane.showInputDialog(null,"How many credits do you spend on each round?", "Welcome to Slot Machine Game!",JOptionPane.INFORMATION_MESSAGE);
        cost += Integer.parseInt(costStr);
        
        // checks for an illegal input for cost per round, and if there is one sets the cost to 1
        if(cost<1)
        {
            error=true;
            cost=1;
        }
        
        // sets the graphics for a 2 of a kind and 3 of a kind varying by cost, and sets the prize for each
        if(cost==1){
            twoWin=1;
            threeWin=5;
            
            twoImage = new ImageIcon("Two_One.png");
            threeImage = new ImageIcon("Three_One.png");
        }
        if(cost==2){
            twoWin=5;
            threeWin=10;
            
            twoImage = new ImageIcon("Two_Two.png");
            threeImage = new ImageIcon("Three_Two.png");
        }
        if(cost==3){
            twoWin=8;
            threeWin=50;
            
            twoImage = new ImageIcon("Two_Three.png");
            threeImage = new ImageIcon("Three_Three.png");
        }
        if(cost==4){
            twoWin=10;
            threeWin=100;
            
            twoImage = new ImageIcon("Two_Four.png");
            threeImage = new ImageIcon("Three_Four.png");
        }
        if(cost==5){
            twoWin=12;
            threeWin=150;
            
            twoImage = new ImageIcon("Two_Five.png");
            threeImage = new ImageIcon("Three_Five.png");
        }
        if(cost==6){
            twoWin=14;
            threeWin=200;
            
            twoImage = new ImageIcon("Two_Six.png");
            threeImage = new ImageIcon("Three_Six.png");
        }
        if(cost==7){
            twoWin=16;
            threeWin=250;
            
            twoImage = new ImageIcon("Two_Seven.png");
            threeImage = new ImageIcon("Three_Seven.png");
        }
        if(cost==8){
            twoWin=18;
            threeWin=300;
            
            twoImage = new ImageIcon("Two_Eight.png");
            threeImage = new ImageIcon("Three_Eight.png");
        }
        if(cost==9){
            twoWin=20;
            threeWin=350;
            
            twoImage = new ImageIcon("Two_Nine.png");
            threeImage = new ImageIcon("Three_Nine.png");
        }
        if(cost==10){
            twoWin=22;
            threeWin=400;
            
            twoImage = new ImageIcon("Two_Ten.png");
            threeImage = new ImageIcon("Three_Ten.png");
        }
        if(cost>10){
            twoWin=(cost*3);
            threeWin=(cost*10);
            
            twoImage = new ImageIcon("Two.png");
            threeImage = new ImageIcon("Three.png");
        }
                
        // announces the cost and prizes and sets the inital and current number of credits for the specfics of this game
        introduction(error);
        String creditsStr=JOptionPane.showInputDialog(null,"How many credits do you want to start with?", "Welcome to Slot Machine Game!",JOptionPane.INFORMATION_MESSAGE);
        credits += Integer.parseInt(creditsStr);
        orgCredits += Integer.parseInt(creditsStr);
    }
    
    public void pullLever()
    {
        // tests that the player has enough credits to continue playing
        if(credits>=cost)
        {
            round ++;
            credits -= cost;

            // sets the round's random numers, tests for matches, and prints the outcome
            for(int i=0;i<3;i++)
            {
                result[i]=(int)(Math.random()*10);
            }

            examine();
            printRound();
        }
        else
        {
            // asks the player to add more credits, and if they do, runs the round, otherwise ends the game
            int add;
            add = JOptionPane.showConfirmDialog(null,"You are out of credits.\nAdd More Credits?","Out of Credits",JOptionPane.INFORMATION_MESSAGE);
            if(add==JOptionPane.YES_OPTION)
            {
                String creditsStr=JOptionPane.showInputDialog(null,"How many credits do you want to add?", "Adding Credits",JOptionPane.INFORMATION_MESSAGE);
                credits += Integer.parseInt(creditsStr);
                totalAdded += Integer.parseInt(creditsStr);
                added=true;
            }
            else
            {
                stop = true;
            }
        }
    }
    
    private void displayRules()
    {
        // explains how the credit to prize conversion works and how numbers are displayed
        JOptionPane.showMessageDialog(null, "You can choose the number of credits for " 
        + "the cost from 1-10.\n"
        + "In this range, the percent you get back on average increases. \n"
        + "After this it goes back down and is constant. \n\n"
        + "When you see the numbers, to simulate a slot machine, the middle, \n" 
        + "bolded row is your numbers, and the ones above and below are what \n"
        + "would appear above and below if you actually spun the machine.", "Welcome to "
        +"Slot Machine Game!", JOptionPane.INFORMATION_MESSAGE,slotImage);
    }
    
    public void introduction(boolean error)
    {
          //prints error message if needed, and displays cost and prizes specific to the game being played
          String str = "";
          if (error)
          {
              str += "You added a cost less than one credit, so it was automatically set to 1.\n";
          }
          JOptionPane.showMessageDialog(null, str + "The cost is " + cost + " credits.\n"
            +"You win " + twoWin + " credits for a two of a kind. \n"
            + "You win " + threeWin + " credits for a three of a kind."
            ,"Welcome to Slot Machine Game!", JOptionPane.INFORMATION_MESSAGE,slotImage);
    }

    private void printRound()
    {
        //sets the variables that represent the numbers above and below the results, and adjusts them for negative or double digit numbers
        int a0 = result[0]-1;
        int a1 = result[1]-1;
        int a2 = result[2]-1;
        int b0 = result[0]+1;
        int b1 = result[1]+1;
        int b2 = result[2]+1;
        if(result[0]==0)
            a0=9;
        if(result[0]==9)
            b0=0;
        if(result[1]==0)
            a1=9;
        if(result[1]==9)
            b1=0;
        if(result[2]==0)
            a2=9;
        if(result[2]==9)
            b2=0;
        
        //sets message as the numbers to be shown, and adjusts the font to indicate the row of results
        JLabel first = new JLabel(""+a0+"   "+a1+"   "+a2);
        JLabel middle = new JLabel(""+result[0]+" "+result[1]+" "+result[2]);
        JLabel last = new JLabel(""+b0+"   "+b1+"   "+b2);
      
        middle.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel[] message = {first, middle, last};
        
        //shows results in dialog box with accurate icon based on the results
        if(result[0]==result[1]&&result[1]==result[2])
        {
            JOptionPane.showMessageDialog(null,message,"Current Balance: " 
                + credits, JOptionPane.INFORMATION_MESSAGE,threeImage);
        }
        else
        {
            if(result[0]==result[1]||result[1]==result[2]||result[0]==result[2])
            {
                JOptionPane.showMessageDialog(null,message,"Current Balance: " 
                    + credits, JOptionPane.INFORMATION_MESSAGE,twoImage);
            }
            else
            {
                JOptionPane.showMessageDialog(null,message,"Current Balance: " 
                    + credits, JOptionPane.INFORMATION_MESSAGE,noImage);
            }
        }

    }

    private void examine()
    {
        //increments the counter for three of a kinds and two of a kinds when necessary, and adds the appropriate number of credits for each win.
        if(result[0]==result[1]&&result[1]==result[2])

        {
            three ++;
            credits += threeWin;
        }
        else
        {
            if(result[0]==result[1]||result[1]==result[2]||result[0]==result[2])
            {
                two ++;
                credits += twoWin;
            }
        }
    }

    //accessors, to be used in the main class
    public int getRound()
    {
        return round;
    }

    public int getCredits()
    {
        return credits;
    }

    public int getThree()
    {
        return three;
    }

    public int getTwo()
    {
        return two;
    }

    public int getCost()
    {
        return cost;
    }

    public boolean getStop()
    {
        return stop;
    }

    //final results, displays relevant information on the game, based on whether credits were added in the middle or not
    public void results()
    {
        String result = ""
            +"Your starting credit was " + orgCredits + ". "
            +"\nYou played " + round + " rounds. "
            +"\nEach round cost " + cost + " credits."
            +"\nYou spent " + (cost*round) + " credits. "
            +"\nYou got " + three + " three of a kinds, and " + two + " two of a kinds. "
            +"\nSo you won " + (three*threeWin + two*twoWin) + " credits!"
            +"\nFor a total balance of " + credits + ".";

        if(added)
        {
            result = ""
            +"Your starting credit was " + orgCredits + ". "
            +"\nYou added " + totalAdded + " credits." 
            +"\nYou played " + round + " rounds. "
            +"\nEach round cost " + cost + " credits."
            +"\nYou spent " + (cost*round) + " credits. "
            +"\nYou got " + three + " three of a kinds, and " + two + " two of a kinds. "
            +"\nSo you won " + (three*threeWin + two*twoWin) + " credits!"
            +"\nFor a total balance of " + credits + ".";
        }
        JOptionPane.showMessageDialog(null,result,"Final Results",0,slotImage);
    }
}
