/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shortcuts;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

/**
 *
 * @author Domryuken
 */
public class State {

    private String[] buttonNames;
    private String[][] allKeys;
    private boolean[] boxes;
    private int[][] shortcuts;

    public State() {
        buttonNames = new String[10];
        allKeys = new String[10][3];
        boxes = new boolean[10];
        shortcuts = new int[10][3];
    }
    
    public boolean exists(){
        File stateFile = new File("./stateFile");
        boolean exists = stateFile.exists();
        return exists;
    }

    public void setAll(JButton[] buttonNames, JTextField[][] allKeys, JCheckBox[] boxes, int[][] shortcuts) {
        for (int i = 0; i < 10; i++) {
            this.buttonNames[i] = buttonNames[i].getText();
            this.boxes[i] = boxes[i].isSelected();
            this.allKeys[i][0] = allKeys[i][0].getText();
            this.allKeys[i][1] = allKeys[i][1].getText();
            this.allKeys[i][2] = allKeys[i][2].getText();
            this.shortcuts[i][0] = shortcuts[i][0];
            this.shortcuts[i][1] = shortcuts[i][1];
            this.shortcuts[i][2] = shortcuts[i][2];
        }
    }

    public String[] getButtonNames() {
        return buttonNames;
    }

    public boolean[] getBoxes() {
        return boxes;
    }

    public String[][] getAllKeys() {
        return allKeys;
    }

    public int[][] getShortcuts() {
        return shortcuts;
    }

    public void saveState() throws Exception {
        File stateFile = new File("./stateFile");
        FileOutputStream fos = new FileOutputStream(stateFile);
        DataOutputStream dos = new DataOutputStream(fos);

        for (int i = 0; i < 10; i++) { //store the buttons in an arraylist when they are created (you could also iterate over the added components and use instanceof)
            dos.writeUTF(buttonNames[i]);
            dos.writeUTF(boxes[i] + "");
            dos.writeUTF(allKeys[i][0]);
            dos.writeUTF(allKeys[i][1]);
            dos.writeUTF(allKeys[i][2]);
            dos.writeUTF(shortcuts[i][0] + "");
            dos.writeUTF(shortcuts[i][1] + "");
            dos.writeUTF(shortcuts[i][2] + "");
        }
        
        dos.flush();
        dos.close();
        fos.flush();
        fos.close();
    }

    public void loadState() throws Exception {
        File stateFile = new File("./stateFile");
        FileInputStream fis = new FileInputStream(stateFile);
        DataInputStream dis = new DataInputStream(fis);
        
        for (int i = 0; i < 10; i++) { //store the buttons in an arraylist when they are created (you could also iterate over the added components and use instanceof)
            buttonNames[i] = dis.readUTF();
            boxes[i] = dis.readUTF().equals("true");
            allKeys[i][0] = dis.readUTF();
            allKeys[i][1] = dis.readUTF();
            allKeys[i][2] = dis.readUTF();
            shortcuts[i][0] = Integer.parseInt(dis.readUTF());
            shortcuts[i][1] = Integer.parseInt(dis.readUTF());
            shortcuts[i][2] = Integer.parseInt(dis.readUTF());
        }
        
        
        fis.close();
    }
}
