import javax.swing.*;
/**
 * Write a description of class SlotDriver here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main
{
    public static void main()
    {
        ImageIcon slotImage = new ImageIcon("Slot.png");
        SlotMachine slot = new SlotMachine();
        
        int again;
        //mechanism to allow player to pull the lever as many times as wanted
        again = JOptionPane.showConfirmDialog(null,"Pull Lever?","Current Balance: " + slot.getCredits(),0,1,slotImage);
        while(again==JOptionPane.YES_OPTION)
        {
            slot.pullLever();
            
            if(slot.getStop())
            {
                again=JOptionPane.NO_OPTION;
            }
            else
            {
                again=JOptionPane.showConfirmDialog(null,"Pull Lever Again?","Current Balance: " + slot.getCredits(),0,1,slotImage);
            }
        }

        slot.results();
    }
}
