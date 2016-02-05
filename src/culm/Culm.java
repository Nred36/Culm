/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package culm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author naree1878
 */
public class Culm extends JApplet implements ActionListener, KeyListener {

    Graphics2D myPic;
    Rectangle e5, e4, e3, e2, e1, e0, f0, f1, f2, f3, f4, f5;
    Timer timer;
    int x = 12, y = 12, speed = 6, aY, aX, range = 6, eSpeed = 1;
    static int hp = 200;
    int[] enemyPosY = new int[6];
    int[] enemyPosX = new int[6];
    int[] enemyHealth = new int[6];
    int[] enemyType = new int[6];

    boolean[] fire = new boolean[6];
    int[] firePos = new int[180];
    int[] mapLayout = new int[16];
    int[] masterMap = new int[16];
    boolean[] room = new boolean[4];
    int numRooms, current, finished = 0;
    boolean aPressed, dPressed, wPressed, sPressed;
    int aRight = 2, aUp = 2;
    boolean[] enemy = new boolean[6];
    boolean starting = true, first = true, running = true;
    public Image master;
    private Image dbImage;
    private Graphics dbg;

    /**
     * @param args the command line arguments
     */
    public Culm() {
        timer = new Timer(60, this);
        timer.setInitialDelay(100);     //starts timer
        timer.start();

        //addMouseMoveListener(this);
        addKeyListener(this);
        enemyPosY[0] = 185;
        enemyPosY[1] = 185;
        enemyPosY[2] = 185;       //sets enemy y postition
        enemyPosY[3] = 300;
        enemyPosY[4] = 300;
        enemyPosY[5] = 300;

        enemyPosX[0] = 175;
        enemyPosX[1] = 300;
        enemyPosX[2] = 400;
        enemyPosX[3] = 175;       //sets enemy x postition
        enemyPosX[4] = 300;
        enemyPosX[5] = 400;
        if (first == false) {
            enemies();
        }
        map();
    }

    public void map() {
        int next = (int) Math.ceil(Math.random() * 16 - 1), numRooms = (int) Math.ceil(Math.random() * 4 + 4), numRoom = 0;        //generates next room and the number of room
        for (int i = 0; i < 16; i++) {
            mapLayout[i] = 5;     //clears the map
        }
        while (numRoom <= numRooms) {   //creates the number rooms specificed above          
        /*
             0 = normal
             1 = start
             2 = empty
             3 = item
             4 = boss
             5 = non
             */

            if (next == 0) {         //depending on what room is generated determines what rooms can be generated
                if (mapLayout[next] == 5) {
                    mapLayout[0] = 0; //sets room to normal
                    numRoom++;
                }
                next = (int) Math.ceil(Math.random() * 2); //choses what room to use next
                if (next == 1) {
                    next = 1;
                } else {
                    next = 4;
                }
            }
            if (next == 1) {
                if (mapLayout[next] == 5) {
                    mapLayout[1] = 0;
                    numRoom++;
                }
                next = (int) Math.ceil(Math.random() * 3);
                if (next == 1) {
                    next = 0;
                } else if (next == 2) {
                    next = 5;
                } else {
                    next = 2;
                }
            }
            if (next == 2) {
                if (mapLayout[next] == 5) {
                    mapLayout[2] = 0;
                    numRoom++;
                }
                next = (int) Math.ceil(Math.random() * 3);
                if (next == 1) {
                    next = 1;
                } else if (next == 2) {
                    next = 6;
                } else {
                    next = 3;
                }
            }
            if (next == 3) {
                if (mapLayout[next] == 5) {
                    mapLayout[3] = 0;
                    numRoom++;
                }
                next = (int) Math.ceil(Math.random() * 2);
                if (next == 1) {
                    next = 2;
                } else {
                    next = 7;
                }
            }
            if (next == 4) {
                if (mapLayout[next] == 5) {
                    mapLayout[4] = 0;
                    numRoom++;
                }
                next = (int) Math.ceil(Math.random() * 3);
                if (next == 1) {
                    next = 0;
                } else if (next == 2) {
                    next = 5;
                } else {
                    next = 8;
                }
            }
            if (next == 5) {
                if (mapLayout[next] == 5) {
                    mapLayout[5] = 0;
                    numRoom++;
                }
                next = (int) Math.ceil(Math.random() * 4);
                if (next == 1) {
                    next = 4;
                } else if (next == 2) {
                    next = 1;
                } else if (next == 3) {
                    next = 6;
                } else {
                    next = 9;
                }
            }
            if (next == 6) {
                if (mapLayout[next] == 5) {
                    mapLayout[6] = 0;
                    numRoom++;
                }
                next = (int) Math.ceil(Math.random() * 4);
                if (next == 1) {
                    next = 5;
                } else if (next == 2) {
                    next = 2;
                } else if (next == 3) {
                    next = 7;
                } else {
                    next = 10;
                }
            }
            if (next == 7) {
                if (mapLayout[next] == 5) {
                    mapLayout[7] = 0;
                    numRoom++;
                }
                next = (int) Math.ceil(Math.random() * 3);
                if (next == 1) {
                    next = 6;
                } else if (next == 2) {
                    next = 3;
                } else {
                    next = 11;
                }
            }
            if (next == 8) {
                if (mapLayout[next] == 5) {
                    mapLayout[8] = 0;
                    numRoom++;
                }
                next = (int) Math.ceil(Math.random() * 3);
                if (next == 1) {
                    next = 4;
                } else if (next == 2) {
                    next = 9;
                } else {
                    next = 12;
                }
            }
            if (next == 9) {
                if (mapLayout[next] == 5) {
                    mapLayout[9] = 0;
                    numRoom++;
                }
                next = (int) Math.ceil(Math.random() * 4);
                if (next == 1) {
                    next = 8;
                } else if (next == 2) {
                    next = 5;
                } else if (next == 3) {
                    next = 10;
                } else {
                    next = 13;
                }
            }
            if (next == 10) {
                if (mapLayout[next] == 5) {
                    mapLayout[10] = 0;
                    numRoom++;
                }
                next = (int) Math.ceil(Math.random() * 4);
                if (next == 1) {
                    next = 9;
                } else if (next == 2) {
                    next = 6;
                } else if (next == 3) {
                    next = 11;
                } else {
                    next = 14;
                }
            }
            if (next == 11) {
                if (mapLayout[next] == 5) {
                    mapLayout[11] = 0;
                    numRoom++;
                }
                next = (int) Math.ceil(Math.random() * 3);
                if (next == 1) {
                    next = 10;
                } else if (next == 2) {
                    next = 7;
                } else {
                    next = 15;
                }
            }
            if (next == 12) {
                if (mapLayout[next] == 5) {
                    mapLayout[12] = 0;
                    numRoom++;
                }
                next = (int) Math.ceil(Math.random() * 2);
                if (next == 1) {
                    next = 8;
                } else {
                    next = 13;
                }
            }
            if (next == 13) {
                if (mapLayout[next] == 5) {
                    mapLayout[13] = 0;
                    numRoom++;
                }
                next = (int) Math.ceil(Math.random() * 3);
                if (next == 1) {
                    next = 12;
                } else if (next == 2) {
                    next = 9;
                } else {
                    next = 14;
                }
            }
            if (next == 14) {
                if (mapLayout[next] == 5) {
                    mapLayout[14] = 0;
                    numRoom++;
                }
                next = (int) Math.ceil(Math.random() * 3);
                if (next == 1) {
                    next = 13;
                } else if (next == 2) {
                    next = 10;
                } else {
                    next = 15;
                }
            }
            if (next == 15) {
                if (mapLayout[next] == 5) {
                    mapLayout[15] = 0;
                    numRoom++;
                }
                next = (int) Math.ceil(Math.random() * 2);
                if (next == 1) {
                    next = 14;
                } else {
                    next = 11;
                }
            }
        }
        boolean set = false;
        while (set == false) {
            for (int c = 0; c < 15; c++) {
                if (mapLayout[c] == 3) {
                    mapLayout[c] = 0;
                }
            }
            int itemRoom = (int) Math.ceil(Math.random() * 15);
            if (mapLayout[itemRoom] == 0) {
                mapLayout[itemRoom] = 3;
                if (mapLayout[14] == 5 && mapLayout[11] != 5) {
                    if (mapLayout[14] == 1 || mapLayout[14] == 3) {
                        map();
                    } else if (mapLayout[11] == 1 || mapLayout[11] == 3) {
                        map();
                    } else {
                        set = true;
                        mapLayout[15] = 4;
                    }
                } else if (mapLayout[11] == 5 && mapLayout[14] != 5) {
                    if (mapLayout[11] == 1 || mapLayout[11] == 3) {
                        map();
                    } else if (mapLayout[15] == 1 || mapLayout[15] == 3) {
                        map();
                    } else {
                        set = true;
                        mapLayout[15] = 4;
                    }
                } else if (mapLayout[8] == 5 && mapLayout[13] != 5) {
                    if (mapLayout[8] == 1 || mapLayout[8] == 3) {
                        map();
                    } else if (mapLayout[13] == 1 || mapLayout[13] == 3) {
                        map();
                    } else {
                        set = true;
                        mapLayout[12] = 4;
                    }
                } else if (mapLayout[13] == 5 && mapLayout[8] != 5) {
                    if (mapLayout[13] == 1 || mapLayout[13] == 3) {
                        map();
                    } else if (mapLayout[8] == 1 || mapLayout[8] == 3) {
                        map();
                    } else {
                        set = true;
                        mapLayout[12] = 4;
                    }
                } else if (mapLayout[2] == 5 && mapLayout[7] != 5) {
                    if (mapLayout[2] == 1 || mapLayout[2] == 3) {
                        map();
                    } else if (mapLayout[7] == 1 || mapLayout[7] == 3) {
                        map();
                    } else {
                        set = true;
                        mapLayout[3] = 4;
                    }
                } else if (mapLayout[7] == 5 && mapLayout[2] != 5) {
                    if (mapLayout[7] == 1 || mapLayout[7] == 3) {
                        map();
                    } else if (mapLayout[2] == 1 || mapLayout[2] == 3) {
                        map();
                    } else {
                        set = true;
                        mapLayout[3] = 4;
                    }
                } else if (mapLayout[1] == 5 && mapLayout[4] != 5) {
                    if (mapLayout[1] == 1 || mapLayout[1] == 3) {
                        map();
                    } else if (mapLayout[4] == 1 || mapLayout[4] == 3) {
                        map();
                    } else {
                        set = true;
                        mapLayout[0] = 4;
                    }
                } else if (mapLayout[4] == 5 && mapLayout[1] != 5) {
                    if (mapLayout[4] == 1 || mapLayout[4] == 3) {
                        map();
                    } else if (mapLayout[1] == 1 || mapLayout[1] == 3) {
                        map();
                    } else {
                        set = true;
                        mapLayout[0] = 4;
                    }
                } else {
                    map();
                }
            }
        }
        for (int i = 0;
                i < 16; i++) {
            masterMap[i] = mapLayout[i];
            if (mapLayout[i] == 0 && starting == true) {
                starting = false;
                current = i;
            }
        }

    }

    public void enemies() {
        /*
         0 Normal
         1 Faster Up - Down
         2 Faster Left - Right
         3 Spawns Fire on Death
         */
        if (mapLayout[current] == 3) {
        } else if (mapLayout[current] == 4) {
            //Boss Code
        } else {
            int numEnimies = (int) Math.ceil(Math.random() * 6);   //choses number of enemies
            for (int n = 0; n < 6; n++) {
                enemy[n] = false;     //resets enemies
                enemyType[n] = 0;
            }
            for (int i = 0; i < numEnimies; i++) {
                enemy[i] = true;      //enables the number enemies decided above      
            }
            for (int h = 0; h < numEnimies; h++) {      //choses the amount of health each enemy has
                enemyHealth[h] = (int) Math.ceil(Math.random() * 14 + 4);
            }
            for (int t = 0; t < numEnimies; t++) {     //choses the type of enemy each is
                enemyType[t] = (int) Math.ceil(Math.random() * 4 - 1);
            }
        }
    }

    public void paint(Graphics g) {
        dbImage = createImage(getWidth(), getHeight());      //creats and image the size of the screen
        dbg = dbImage.getGraphics();        //double buffers the panel
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }

    public void paintComponent(Graphics g) {

        myPic = (Graphics2D) g;
        if (finished == 16) {
            master = new ImageIcon("Win.png").getImage();
            g.drawImage(master, 0, 0, null);

        } else if (hp <= 0) {    //checks if your died
            master = new ImageIcon("Lose.png").getImage();   //tells you lost
            g.drawImage(master, 0, 0, null);
        } else if (running == false) {      //checks if the game is paused
            g.drawString("Game Paused", 380, 290);      //tells you the game is paused
        } else {
            master = new ImageIcon("Heart.png").getImage();   //gets heart
            if (hp >= 10) {
                g.drawImage(master, 0, 0, null);
            }
            if (hp >= 20) {
                g.drawImage(master, 15, 0, null);
            }
            if (hp >= 30) {
                g.drawImage(master, 30, 0, null);
            }
            if (hp >= 40) {
                g.drawImage(master, 45, 0, null);
            }
            if (hp >= 50) {
                g.drawImage(master, 60, 0, null);
            }
            if (hp >= 60) {
                g.drawImage(master, 75, 0, null);
            }
            if (hp >= 70) {
                g.drawImage(master, 90, 0, null);
            }
            if (hp >= 80) {
                g.drawImage(master, 105, 0, null);
            }
            if (hp >= 90) {
                g.drawImage(master, 120, 0, null);
            }
            if (hp >= 100) {
                g.drawImage(master, 135, 0, null);
            }
            if (hp >= 110) {
                g.drawImage(master, 150, 0, null);
            }
            if (hp >= 120) {
                g.drawImage(master, 165, 0, null);
            }
            if (hp >= 130) {
                g.drawImage(master, 180, 0, null);
            }
            if (hp >= 140) {
                g.drawImage(master, 195, 0, null);
            }
            if (hp >= 150) {
                g.drawImage(master, 210, 0, null);
            }
            if (hp >= 160) {
                g.drawImage(master, 225, 0, null);
            }
            if (hp >= 170) {
                g.drawImage(master, 240, 0, null);
            }
            if (hp >= 180) {
                g.drawImage(master, 255, 0, null);
            }
            if (hp >= 190) {
                g.drawImage(master, 270, 0, null);
            }
            if (hp >= 200) {
                g.drawImage(master, 285, 0, null);
            }

            if (masterMap[current] == 3) {
                myPic.setPaint(new Color(128, 128, 128));
                g.fillRect(360, 285, 80, 30);
            }

            Rectangle p = new Rectangle(x, y, 70, 120);        //creates an invisible rectangle around the player

            if (fire[0] == true) {     //checks if the fire is there
                f0 = new Rectangle(enemyPosX[0] - 2, enemyPosY[0] - 2, 75, 125);      //creates an invisible rectangle around the fire
                if (f0.intersects(p)) {      //check if the player is touching the fire
                    System.out.println("Hp: " + hp);
                    hp--;      //removes health
                }
            }
            if (fire[1] == true) {
                f1 = new Rectangle(enemyPosX[1] - 2, enemyPosY[1] - 2, 75, 125);
                if (f1.intersects(p)) {
                    System.out.println("Hp: " + hp);
                    hp--;
                }
            }
            if (fire[2] == true) {
                f2 = new Rectangle(enemyPosX[2] - 2, enemyPosY[2] - 2, 75, 125);
                if (f2.intersects(p)) {
                    System.out.println("Hp: " + hp);
                    hp--;
                }
            }
            if (fire[3] == true) {
                f3 = new Rectangle(enemyPosX[3] - 2, enemyPosY[3] - 2, 75, 125);
                if (f3.intersects(p)) {
                    System.out.println("Hp: " + hp);
                    hp--;
                }
            }
            if (fire[4] == true) {
                f4 = new Rectangle(enemyPosX[4] - 2, enemyPosY[4] - 2, 75, 125);
                if (f4.intersects(p)) {
                    System.out.println("Hp: " + hp);
                    hp--;
                }
            }
            if (fire[5] == true) {
                f5 = new Rectangle(enemyPosX[5] - 2, enemyPosY[5] - 2, 75, 125);
                if (f5.intersects(p)) {
                    System.out.println("Hp: " + hp);
                    hp--;
                }
            }

            if (enemy[0] == true) {      //checks if an enemy is there
                e0 = new Rectangle(enemyPosX[0], enemyPosY[0], 70, 120);      //creates an invisible rectangle around the enemy
                if (e0.intersects(p)) {
                    System.out.println("Hp: " + hp);
                    hp--;
                }
            }
            if (enemy[1] == true) {
                e1 = new Rectangle(enemyPosX[1], enemyPosY[1], 70, 120);
                if (e1.intersects(p)) {
                    System.out.println("Hp: " + hp);
                    hp--;
                }
            }
            if (enemy[2] == true) {
                e2 = new Rectangle(enemyPosX[2], enemyPosY[2], 70, 120);
                if (e2.intersects(p)) {
                    System.out.println("Hp: " + hp);
                    hp--;
                }
            }
            if (enemy[3] == true) {
                e3 = new Rectangle(enemyPosX[3], enemyPosY[3], 70, 120);
                if (e3.intersects(p)) {
                    System.out.println("Hp: " + hp);
                    hp--;
                }
            }
            if (enemy[4] == true) {
                e4 = new Rectangle(enemyPosX[4], enemyPosY[4], 70, 120);
                if (e4.intersects(p)) {
                    System.out.println("Hp: " + hp);
                    hp--;
                }
            }
            if (enemy[5] == true) {
                e5 = new Rectangle(enemyPosX[5], enemyPosY[5], 70, 120);
                if (e5.intersects(p)) {
                    System.out.println("Hp: " + hp);
                    hp--;
                }
            }

            if (enemy[0] == false && enemy[1] == false && enemy[2] == false && enemy[3] == false && enemy[4] == false && enemy[5] == false) {
                myPic.setPaint(new Color(139, 69, 19));       //if the room is clear set the door color to brown
            } else {
                myPic.setPaint(Color.BLACK);
            }
            if (room[0] == true) {    //if there is a room in the directoin put a door there
                myPic.fillRect(0, 200, 25, 167);//Door Left
            } else if (room[1] == true) {
                myPic.fillRect(313, 0, 167, 25);//Door Up
            }
            if (room[2] == true) {
                myPic.fillRect(769, 200, 25, 167);//Door Right
            }
            if (room[3] == true) {
                myPic.fillRect(313, 547, 167, 25);//Door Down
            }

            if (enemyHealth[0] == 0) { //stops drawing enemies if there died
                enemy[0] = false;
            }
            if (enemyHealth[1] == 0) {
                enemy[1] = false;
            }
            if (enemyHealth[2] == 0) {
                enemy[2] = false;
            }
            if (enemyHealth[3] == 0) {
                enemy[3] = false;
            }
            if (enemyHealth[4] == 0) {
                enemy[4] = false;
            }
            if (enemyHealth[5] == 0) {
                enemy[5] = false;
            }
            if (aRight == 0) {//if left is pushed
                aX = x - 149; //sets the arrow position
                aY = y + 36;
                Image master = new ImageIcon("ArrowLeft.png").getImage();
                g.drawImage(master, aX + 70, aY + 12, null);        //draws the arrow                       
            } else if (aRight == 1) {//Right
                aX = x + 64;
                aY = y + 36;
                Image master = new ImageIcon("ArrowRight.png").getImage();
                g.drawImage(master, aX + 70, aY, null);
            } else if (aUp == 0) {//Down
                aX = x - 40;
                aY = y + 184;
                Image master = new ImageIcon("ArrowDown.png").getImage();
                g.drawImage(master, aX + 70, aY + 12, 15, 15, null);
            } else if (aUp == 1) {//Up
                aX = x - 30;
                aY = y - 98;
                Image master = new ImageIcon("ArrowUp.png").getImage();
                g.drawImage(master, aX + 60, aY + 12, null);
            }
            if (aRight == 0 || aRight == 1 || aUp == 0 || aUp == 1) {
                if (aX < enemyPosX[0] && aX + 74 > enemyPosX[0] && aY > enemyPosY[0] && aY - 120 < enemyPosY[0]) {
                    enemyHealth[0] -= 1;       //checks if the arrow is inside the enemy removes health
                }
                if (aX < enemyPosX[1] && aX + 74 > enemyPosX[1] && aY > enemyPosY[1] && aY - 120 < enemyPosY[1]) {
                    enemyHealth[1] -= 1;
                }
                if (aX < enemyPosX[2] && aX + 74 > enemyPosX[2] && aY > enemyPosY[2] && aY - 120 < enemyPosY[2]) {
                    enemyHealth[2] -= 1;
                }
                if (aX < enemyPosX[3] && aX + 74 > enemyPosX[3] && aY > enemyPosY[3] && aY - 120 < enemyPosY[3]) {
                    enemyHealth[3] -= 1;
                }
                if (aX < enemyPosX[4] && aX + 74 > enemyPosX[4] && aY > enemyPosY[4] && aY - 120 < enemyPosY[4]) {
                    enemyHealth[4] -= 1;
                }
                if (aX < enemyPosX[5] && aX + 74 > enemyPosX[5] && aY > enemyPosY[5] && aY - 120 < enemyPosY[5]) {
                    enemyHealth[5] -= 1;
                }
            }
            master = new ImageIcon("player.png").getImage();
            g.drawImage(master, x, y, 70, 120, null);  //draws player
            //g.fillRect(x, y, 70, 120);
            fire[0] = false;
            fire[1] = false;
            fire[2] = false;  //resets fire
            fire[3] = false;
            fire[4] = false;
            fire[5] = false;
            if (mapLayout[current] == 0) {
                if (enemy[0] == false && enemyType[0] == 3) {
                    Image master = new ImageIcon("Fire.png").getImage();
                    g.drawImage(master, enemyPosX[0] - 2, enemyPosY[0] - 2, 75, 125, null);
                    fire[0] = true;            //if there is fire draw it     
                    firePos[current * 5] = enemyPosX[0];
                    firePos[current * 5 + 90] = enemyPosY[0];
                }
                if (enemy[1] == false && enemyType[1] == 3) {
                    Image master = new ImageIcon("Fire.png").getImage();
                    g.drawImage(master, enemyPosX[1] - 2, enemyPosY[1] - 2, 75, 125, null);
                    fire[1] = true;
                    firePos[current * 5 + 1] = enemyPosX[1];
                    firePos[current * 5 + 91] = enemyPosY[1];
                }
                if (enemy[2] == false && enemyType[2] == 3) {
                    Image master = new ImageIcon("Fire.png").getImage();
                    g.drawImage(master, enemyPosX[2] - 2, enemyPosY[2] - 2, 75, 125, null);
                    fire[2] = true;
                    firePos[current * 5 + 2] = enemyPosX[2];
                    firePos[current * 5 + 92] = enemyPosY[2];
                }
                if (enemy[3] == false && enemyType[3] == 3) {
                    Image master = new ImageIcon("Fire.png").getImage();
                    g.drawImage(master, enemyPosX[3] - 2, enemyPosY[3] - 2, 75, 125, null);
                    fire[3] = true;
                    firePos[current * 5 + 3] = enemyPosX[3];
                    firePos[current * 5 + 93] = enemyPosY[3];
                }
                if (enemy[4] == false && enemyType[4] == 3) {
                    Image master = new ImageIcon("Fire.png").getImage();
                    g.drawImage(master, enemyPosX[4] - 2, enemyPosY[4] - 2, 75, 125, null);
                    fire[4] = true;
                    firePos[current * 5 + 4] = enemyPosX[4];
                    firePos[current * 5 + 94] = enemyPosY[4];
                }
                if (enemy[5] == false && enemyType[5] == 3) {
                    Image master = new ImageIcon("Fire.png").getImage();
                    g.drawImage(master, enemyPosX[5] - 2, enemyPosY[5] - 2, 75, 125, null);
                    fire[5] = true;
                    firePos[current * 5 + 5] = enemyPosX[5];
                    firePos[current * 5 + 95] = enemyPosY[5];
                }
            } else {
                if (firePos[current * 5] != 0) {
                    Image master = new ImageIcon("Fire.png").getImage();
                    g.drawImage(master, firePos[current * 5] - 2, firePos[current * 5 + 90] - 2, 75, 125, null);
                    fire[0] = true;
                }
                if (firePos[current * 5 + 1] != 0) {
                    Image master = new ImageIcon("Fire.png").getImage();
                    g.drawImage(master, firePos[current * 5] - 2, firePos[current * 5 + 91] - 2, 75, 125, null);
                    fire[1] = true;
                }
                if (firePos[current * 5 + 2] != 0) {
                    Image master = new ImageIcon("Fire.png").getImage();
                    g.drawImage(master, firePos[current * 5] - 2, firePos[current * 5 + 92] - 2, 75, 125, null);
                    fire[2] = true;
                }
                if (firePos[current * 5 + 3] != 0) {
                    Image master = new ImageIcon("Fire.png").getImage();
                    g.drawImage(master, firePos[current * 5] - 2, firePos[current * 5 + 93] - 2, 75, 125, null);
                    fire[3] = true;
                }
                if (firePos[current * 5 + 4] != 0) {
                    Image master = new ImageIcon("Fire.png").getImage();
                    g.drawImage(master, firePos[current * 5] - 2, firePos[current * 5 + 94] - 2, 75, 125, null);
                    fire[4] = true;
                }
                if (firePos[current * 5 + 5] != 0) {
                    Image master = new ImageIcon("Fire.png").getImage();
                    g.drawImage(master, firePos[current * 5] - 2, firePos[current * 5 + 95] - 2, 75, 125, null);
                    fire[5] = true;
                }

            }
            if (enemy[0] == true) {        //if the enemy is alive
                if (enemyType[0] == 0 || enemyType[0] == 3) {    //checks enemy type            
                    if (x < enemyPosX[0]) {            //moves according to players positon
                        enemyPosX[0] -= eSpeed;
                    } else if (x > enemyPosX[0]) {
                        enemyPosX[0] += eSpeed;
                    }
                    if (y < enemyPosY[0]) {
                        enemyPosY[0] -= eSpeed;
                    } else if (y > enemyPosY[0]) {
                        enemyPosY[0] += eSpeed;
                    }
                } else if (enemyType[0] == 1) {        //checks enemy type
                    if (x < enemyPosX[0]) {
                        enemyPosX[0] -= eSpeed;         //moves according to players position           
                    } else if (x > enemyPosX[0]) {
                        enemyPosX[0] += eSpeed;
                    }
                    if (y < enemyPosY[0]) {
                        enemyPosY[0] -= eSpeed + 1;
                    } else if (y > enemyPosY[0]) {
                        enemyPosY[0] += eSpeed + 1;
                    }
                } else if (enemyType[0] == 2) {
                    if (x < enemyPosX[0]) {
                        enemyPosX[0] -= eSpeed + 1;
                    } else if (x > enemyPosX[0]) {
                        enemyPosX[0] += eSpeed + 1;
                    }
                    if (y < enemyPosY[0]) {
                        enemyPosY[0] -= eSpeed;
                    } else if (y > enemyPosY[0]) {
                        enemyPosY[0] += eSpeed;
                    }
                }
                if (enemyType[0] == 3) {
                    Image master = new ImageIcon("enemyFlame.png").getImage();
                    g.drawImage(master, enemyPosX[0], enemyPosY[0], 70, 120, null);  //draws fire enemies
                } else {
                    Image master = new ImageIcon("enemyNorm.png").getImage();
                    g.drawImage(master, enemyPosX[0], enemyPosY[0], 70, 120, null); //draw enemies
                }
            }
            if (enemy[1] == true) {
                if (enemyType[1] == 0 || enemyType[1] == 3) {
                    if (x < enemyPosX[1]) {
                        enemyPosX[1] -= eSpeed;
                    } else if (x > enemyPosX[1]) {
                        enemyPosX[1] += eSpeed;
                    }
                    if (y < enemyPosY[1]) {
                        enemyPosY[1] -= eSpeed;
                    } else if (y > enemyPosY[1]) {
                        enemyPosY[1] += eSpeed;
                    }
                } else if (enemyType[1] == 1) {
                    if (x < enemyPosX[1]) {
                        enemyPosX[1] -= eSpeed;
                    } else if (x > enemyPosX[1]) {
                        enemyPosX[1] += eSpeed;
                    }
                    if (y < enemyPosY[1]) {
                        enemyPosY[1] -= eSpeed + 1;
                    } else if (y > enemyPosY[1]) {
                        enemyPosY[1] += eSpeed + 1;
                    }
                } else if (enemyType[1] == 2) {
                    if (x < enemyPosX[1]) {
                        enemyPosX[1] -= eSpeed + 1;
                    } else if (x > enemyPosX[1]) {
                        enemyPosX[1] += eSpeed + 1;
                    }
                    if (y < enemyPosY[1]) {
                        enemyPosY[1] -= eSpeed;
                    } else if (y > enemyPosY[1]) {
                        enemyPosY[1] += eSpeed;
                    }
                }
                if (enemyType[1] == 3) {
                    Image master = new ImageIcon("enemyFlame.png").getImage();
                    g.drawImage(master, enemyPosX[1], enemyPosY[1], 70, 120, null);
                } else {
                    Image master = new ImageIcon("enemyNorm.png").getImage();
                    g.drawImage(master, enemyPosX[1], enemyPosY[1], 70, 120, null);
                }
            }
            if (enemy[2] == true) {
                if (enemyType[2] == 0 || enemyType[2] == 3) {
                    if (x < enemyPosX[2]) {
                        enemyPosX[2] -= eSpeed;
                    } else if (x > enemyPosX[2]) {
                        enemyPosX[2] += eSpeed;
                    }
                    if (y < enemyPosY[2]) {
                        enemyPosY[2] -= eSpeed;
                    } else if (y > enemyPosY[2]) {
                        enemyPosY[2] += eSpeed;
                    }
                } else if (enemyType[2] == 1) {
                    if (x < enemyPosX[2]) {
                        enemyPosX[2] -= eSpeed;
                    } else if (x > enemyPosX[2]) {
                        enemyPosX[2] += eSpeed;
                    }
                    if (y < enemyPosY[2]) {
                        enemyPosY[2] -= eSpeed + 1;
                    } else if (y > enemyPosY[2]) {
                        enemyPosY[2] += eSpeed + 1;
                    }
                } else if (enemyType[2] == 2) {
                    if (x < enemyPosX[2]) {
                        enemyPosX[2] -= eSpeed + 1;
                    } else if (x > enemyPosX[2]) {
                        enemyPosX[2] += eSpeed + 1;
                    }
                    if (y < enemyPosY[2]) {
                        enemyPosY[2] -= eSpeed;
                    } else if (y > enemyPosY[2]) {
                        enemyPosY[2] += eSpeed;
                    }
                }
                if (enemyType[2] == 3) {
                    Image master = new ImageIcon("enemyFlame.png").getImage();
                    g.drawImage(master, enemyPosX[2], enemyPosY[2], 70, 120, null);
                } else {
                    Image master = new ImageIcon("enemyNorm.png").getImage();
                    g.drawImage(master, enemyPosX[2], enemyPosY[2], 70, 120, null);
                }
            }

            if (enemy[3] == true) {
                if (enemyType[3] == 0 || enemyType[3] == 3) {
                    if (x < enemyPosX[3]) {
                        enemyPosX[3] -= eSpeed;
                    } else if (x > enemyPosX[3]) {
                        enemyPosX[3] += eSpeed;
                    }
                    if (y < enemyPosY[3]) {
                        enemyPosY[3] -= eSpeed;
                    } else if (y > enemyPosY[3]) {
                        enemyPosY[3] += eSpeed;
                    }
                } else if (enemyType[3] == 1) {
                    if (x < enemyPosX[3]) {
                        enemyPosX[3] -= eSpeed;
                    } else if (x > enemyPosX[3]) {
                        enemyPosX[3] += eSpeed;
                    }
                    if (y < enemyPosY[3]) {
                        enemyPosY[3] -= eSpeed + 1;
                    } else if (y > enemyPosY[3]) {
                        enemyPosY[3] += eSpeed + 1;
                    }
                } else if (enemyType[3] == 2) {
                    if (x < enemyPosX[3]) {
                        enemyPosX[3] -= eSpeed + 1;
                    } else if (x > enemyPosX[3]) {
                        enemyPosX[3] += eSpeed + 1;
                    }
                    if (y < enemyPosY[3]) {
                        enemyPosY[3] -= eSpeed;
                    } else if (y > enemyPosY[3]) {
                        enemyPosY[3] += eSpeed;
                    }
                }
                if (enemyType[3] == 3) {
                    Image master = new ImageIcon("enemyFlame.png").getImage();
                    g.drawImage(master, enemyPosX[3], enemyPosY[3], 70, 120, null);
                } else {
                    Image master = new ImageIcon("enemyNorm.png").getImage();
                    g.drawImage(master, enemyPosX[3], enemyPosY[3], 70, 120, null);
                }
            }
            if (enemy[4] == true) {
                if (enemyType[4] == 0 || enemyType[4] == 3) {
                    if (x < enemyPosX[4]) {
                        enemyPosX[4] -= eSpeed;
                    } else if (x > enemyPosX[4]) {
                        enemyPosX[4] += eSpeed;
                    }
                    if (y < enemyPosY[4]) {
                        enemyPosY[4] -= eSpeed;
                    } else if (y > enemyPosY[4]) {
                        enemyPosY[4] += eSpeed;
                    }
                } else if (enemyType[4] == 1) {
                    if (x < enemyPosX[4]) {
                        enemyPosX[4] -= eSpeed;
                    } else if (x > enemyPosX[4]) {
                        enemyPosX[4] += eSpeed;
                    }
                    if (y < enemyPosY[4]) {
                        enemyPosY[4] -= eSpeed + 1;
                    } else if (y > enemyPosY[4]) {
                        enemyPosY[4] += eSpeed + 1;
                    }
                } else if (enemyType[4] == 2) {
                    if (x < enemyPosX[4]) {
                        enemyPosX[4] -= eSpeed + 1;
                    } else if (x > enemyPosX[4]) {
                        enemyPosX[4] += eSpeed + 1;
                    }
                    if (y < enemyPosY[4]) {
                        enemyPosY[4] -= eSpeed;
                    } else if (y > enemyPosY[4]) {
                        enemyPosY[4] += eSpeed;
                    }
                }
                if (enemyType[4] == 3) {
                    Image master = new ImageIcon("enemyFlame.png").getImage();
                    g.drawImage(master, enemyPosX[4], enemyPosY[4], 70, 120, null);
                } else {
                    Image master = new ImageIcon("enemyNorm.png").getImage();
                    g.drawImage(master, enemyPosX[4], enemyPosY[4], 70, 120, null);
                }
            }
            if (enemy[5] == true) {
                if (enemyType[5] == 0 || enemyType[5] == 3) {
                    if (x < enemyPosX[5]) {
                        enemyPosX[5] -= eSpeed;
                    } else if (x > enemyPosX[5]) {
                        enemyPosX[5] += eSpeed;
                    }
                    if (y < enemyPosY[5]) {
                        enemyPosY[5] -= eSpeed;
                    } else if (y > enemyPosY[5]) {
                        enemyPosY[5] += eSpeed;
                    }
                } else if (enemyType[5] == 1) {
                    if (x < enemyPosX[5]) {
                        enemyPosX[5] -= eSpeed;
                    } else if (x > enemyPosX[5]) {
                        enemyPosX[5] += eSpeed;
                    }
                    if (y < enemyPosY[5]) {
                        enemyPosY[5] -= eSpeed + 1;
                    } else if (y > enemyPosY[5]) {
                        enemyPosY[5] += eSpeed + 1;
                    }
                } else if (enemyType[5] == 2) {
                    if (x < enemyPosX[5]) {
                        enemyPosX[5] -= eSpeed + 1;
                    } else if (x > enemyPosX[5]) {
                        enemyPosX[5] += eSpeed + 1;
                    }
                    if (y < enemyPosY[5]) {
                        enemyPosY[5] -= eSpeed;
                    } else if (y > enemyPosY[5]) {
                        enemyPosY[5] += eSpeed;
                    }
                }
                if (enemyType[5] == 3) {
                    Image master = new ImageIcon("enemyFlame.png").getImage();
                    g.drawImage(master, enemyPosX[5], enemyPosY[5], 70, 120, null);
                } else {
                    Image master = new ImageIcon("enemyNorm.png").getImage();
                    g.drawImage(master, enemyPosX[5], enemyPosY[5], 70, 120, null);
                }
            }
            myPic.setPaint(Color.LIGHT_GRAY);   //draws the border for map
            myPic.fillRect(694, 0, 100, 100);
            myPic.setPaint(Color.black);        //draws all rooms
            myPic.fillRect(698, 4, 20, 20);
            myPic.fillRect(698, 28, 20, 20);
            myPic.fillRect(698, 52, 20, 20);
            myPic.fillRect(698, 76, 20, 20);
            myPic.fillRect(722, 4, 20, 20);
            myPic.fillRect(722, 28, 20, 20);
            myPic.fillRect(722, 52, 20, 20);
            myPic.fillRect(722, 76, 20, 20);
            myPic.fillRect(746, 4, 20, 20);
            myPic.fillRect(746, 28, 20, 20);
            myPic.fillRect(746, 52, 20, 20);
            myPic.fillRect(746, 76, 20, 20);
            myPic.fillRect(770, 4, 20, 20);
            myPic.fillRect(770, 28, 20, 20);
            myPic.fillRect(770, 52, 20, 20);
            myPic.fillRect(770, 76, 20, 20);

            myPic.setPaint(Color.gray);

            if (mapLayout[0] == 0) {       //checks if a room is in each position an draws it there
                myPic.fillRect(698, 4, 20, 20);
            }
            if (mapLayout[1] == 0) {
                myPic.fillRect(722, 4, 20, 20);
            }
            if (mapLayout[2] == 0) {
                myPic.fillRect(746, 4, 20, 20);
            }
            if (mapLayout[3] == 0) {
                myPic.fillRect(770, 4, 20, 20);
            }
            if (mapLayout[4] == 0) {
                myPic.fillRect(698, 28, 20, 20);
            }
            if (mapLayout[5] == 0) {
                myPic.fillRect(722, 28, 20, 20);
            }
            if (mapLayout[6] == 0) {
                myPic.fillRect(746, 28, 20, 20);
            }
            if (mapLayout[7] == 0) {
                myPic.fillRect(770, 28, 20, 20);
            }
            if (mapLayout[8] == 0) {
                myPic.fillRect(698, 52, 20, 20);
            }
            if (mapLayout[9] == 0) {
                myPic.fillRect(722, 52, 20, 20);
            }
            if (mapLayout[10] == 0) {
                myPic.fillRect(746, 52, 20, 20);
            }
            if (mapLayout[11] == 0) {
                myPic.fillRect(770, 52, 20, 20);
            }
            if (mapLayout[12] == 0) {
                myPic.fillRect(698, 76, 20, 20);
            }
            if (mapLayout[13] == 0) {
                myPic.fillRect(722, 76, 20, 20);
            }
            if (mapLayout[14] == 0) {
                myPic.fillRect(746, 76, 20, 20);
            }
            if (mapLayout[15] == 0) {
                myPic.fillRect(770, 76, 20, 20);
            }

            myPic.setColor(new Color(227, 227, 227));

            if (mapLayout[0] == 2) {
                if (masterMap[0] == 3) {
                    myPic.setColor(new Color(240, 230, 140));
                    myPic.fillRect(698, 4, 20, 20);     //changes colour if the room has been visited
                }
            }
            if (mapLayout[1] == 2) {
                if (masterMap[1] == 3) {
                    myPic.setColor(new Color(240, 230, 140));
                    myPic.fillRect(722, 4, 20, 20);
                }
            }
            if (mapLayout[2] == 2) {
                if (masterMap[2] == 3) {
                    myPic.setColor(new Color(240, 230, 140));
                    myPic.fillRect(698, 4, 20, 20);     //changes colour if the room has been visited
                }
            }
            if (mapLayout[3] == 2) {
                if (masterMap[3] == 3) {
                    myPic.setColor(new Color(240, 230, 140));
                    myPic.fillRect(722, 4, 20, 20);
                }
            }
            if (mapLayout[4] == 2) {
                if (masterMap[4] == 3) {
                    myPic.setColor(new Color(240, 230, 140));
                    myPic.fillRect(698, 4, 20, 20);     //changes colour if the room has been visited
                }
            }
            if (mapLayout[5] == 2) {
                if (masterMap[5] == 3) {
                    myPic.setColor(new Color(240, 230, 140));
                    myPic.fillRect(722, 4, 20, 20);
                }
            }
            if (mapLayout[6] == 2) {
                if (masterMap[6] == 3) {
                    myPic.setColor(new Color(240, 230, 140));
                    myPic.fillRect(698, 4, 20, 20);     //changes colour if the room has been visited
                }
            }
            if (mapLayout[7] == 2) {
                if (masterMap[7] == 3) {
                    myPic.setColor(new Color(240, 230, 140));
                    myPic.fillRect(722, 4, 20, 20);
                }
            }
            if (mapLayout[8] == 2) {
                if (masterMap[8] == 3) {
                    myPic.setColor(new Color(240, 230, 140));
                    myPic.fillRect(698, 4, 20, 20);     //changes colour if the room has been visited
                }
            }
            if (mapLayout[9] == 2) {
                if (masterMap[9] == 3) {
                    myPic.setColor(new Color(240, 230, 140));
                    myPic.fillRect(722, 4, 20, 20);
                }
            }
            if (mapLayout[10] == 2) {
                if (masterMap[10] == 3) {
                    myPic.setColor(new Color(240, 230, 140));
                    myPic.fillRect(698, 4, 20, 20);     //changes colour if the room has been visited
                }
            }
            if (mapLayout[11] == 2) {
                if (masterMap[11] == 3) {
                    myPic.setColor(new Color(240, 230, 140));
                    myPic.fillRect(722, 4, 20, 20);
                }
            }
            if (mapLayout[12] == 2) {
                if (masterMap[12] == 3) {
                    myPic.setColor(new Color(240, 230, 140));
                    myPic.fillRect(698, 4, 20, 20);     //changes colour if the room has been visited
                }
            }
            if (mapLayout[13] == 2) {
                if (masterMap[13] == 3) {
                    myPic.setColor(new Color(240, 230, 140));
                    myPic.fillRect(722, 4, 20, 20);
                }
            }
            if (mapLayout[14] == 2) {
                if (masterMap[14] == 3) {
                    myPic.setColor(new Color(240, 230, 140));
                    myPic.fillRect(698, 4, 20, 20);     //changes colour if the room has been visited
                }
            }
            if (mapLayout[15] == 2) {
                if (masterMap[15] == 3) {
                    myPic.setColor(new Color(240, 230, 140));
                    myPic.fillRect(722, 4, 20, 20);
                }
            }

            myPic.setColor(new Color(227, 227, 227));
            for (int r = 0; r < 4; r++) {
                room[r] = false;  //resets the adjesent room
            }
            myPic.setColor(new Color(218, 165, 32));

            if (mapLayout[0] == 3) {       //checks if room is item room
                myPic.fillRect(698, 4, 20, 20);
            }
            if (mapLayout[1] == 3) {
                myPic.fillRect(722, 4, 20, 20);
            }
            if (mapLayout[2] == 3) {
                myPic.fillRect(746, 4, 20, 20);
            }
            if (mapLayout[3] == 3) {
                myPic.fillRect(770, 4, 20, 20);
            }
            if (mapLayout[4] == 3) {
                myPic.fillRect(698, 28, 20, 20);
            }
            if (mapLayout[5] == 3) {
                myPic.fillRect(722, 28, 20, 20);
            }
            if (mapLayout[6] == 3) {
                myPic.fillRect(746, 28, 20, 20);
            }
            if (mapLayout[7] == 3) {
                myPic.fillRect(770, 28, 20, 20);
            }
            if (mapLayout[8] == 3) {
                myPic.fillRect(698, 52, 20, 20);
            }
            if (mapLayout[9] == 3) {
                myPic.fillRect(722, 52, 20, 20);
            }
            if (mapLayout[10] == 3) {
                myPic.fillRect(746, 52, 20, 20);
            }
            if (mapLayout[11] == 3) {
                myPic.fillRect(770, 52, 20, 20);
            }
            if (mapLayout[12] == 3) {
                myPic.fillRect(698, 76, 20, 20);
            }
            if (mapLayout[13] == 3) {
                myPic.fillRect(722, 76, 20, 20);
            }
            if (mapLayout[14] == 3) {
                myPic.fillRect(746, 76, 20, 20);
            }
            if (mapLayout[15] == 3) {
                myPic.fillRect(770, 76, 20, 20);
            }

            myPic.setColor(new Color(128, 0, 0));
            if (mapLayout[0] == 4) {       //checks if room is boss room
                myPic.fillRect(698, 4, 20, 20);
            }
            if (mapLayout[3] == 4) {
                myPic.fillRect(770, 4, 20, 20);
            }
            if (mapLayout[12] == 4) {
                myPic.fillRect(698, 76, 20, 20);
            }
            if (mapLayout[15] == 4) {
                myPic.fillRect(770, 76, 20, 20);
            }

            myPic.setPaint(new Color(82, 86, 107));
            if (current == 0) {        //sets colour for current room
                if (masterMap[current] == 3) {
                    myPic.setPaint(new Color(255, 215, 0));
                }
                myPic.fillRect(698, 4, 20, 20);
                if (mapLayout[1] != 5) {
                    room[2] = true;       //sets adjecent rooms
                }
                if (mapLayout[4] != 5) {
                    room[3] = true;
                }
            }
            if (current == 1) {
                if (masterMap[current] == 3) {
                    myPic.setPaint(new Color(255, 215, 0));
                }
                myPic.fillRect(722, 4, 20, 20);
                if (mapLayout[0] != 5) {
                    room[0] = true;
                }
                if (mapLayout[2] != 5) {
                    room[2] = true;
                }
                if (mapLayout[5] != 5) {
                    room[3] = true;
                }
            }
            if (current == 2) {
                if (masterMap[current] == 3) {
                    myPic.setPaint(new Color(255, 215, 0));
                }
                myPic.fillRect(746, 4, 20, 20);
                if (mapLayout[1] != 5) {
                    room[0] = true;
                }
                if (mapLayout[3] != 5) {
                    room[2] = true;
                }
                if (mapLayout[6] != 5) {
                    room[3] = true;
                }
            }
            if (current == 3) {
                if (masterMap[current] == 3) {
                    myPic.setPaint(new Color(255, 215, 0));
                }
                myPic.fillRect(770, 4, 20, 20);
                if (mapLayout[2] != 5) {
                    room[0] = true;
                }
                if (mapLayout[7] != 5) {
                    room[3] = true;
                }
            }

            if (current == 4) {
                if (masterMap[current] == 3) {
                    myPic.setPaint(new Color(255, 215, 0));
                }
                myPic.fillRect(698, 28, 20, 20);
                if (mapLayout[0] != 5) {
                    room[1] = true;
                }
                if (mapLayout[5] != 5) {
                    room[2] = true;
                }
                if (mapLayout[8] != 5) {
                    room[3] = true;
                }
            }
            if (current == 5) {
                if (masterMap[current] == 3) {
                    myPic.setPaint(new Color(255, 215, 0));
                }
                myPic.fillRect(722, 28, 20, 20);
                if (mapLayout[4] != 5) {
                    room[0] = true;
                }
                if (mapLayout[1] != 5) {
                    room[1] = true;
                }
                if (mapLayout[6] != 5) {
                    room[2] = true;
                }
                if (mapLayout[9] != 5) {
                    room[3] = true;
                }
            }
            if (current == 6) {
                if (masterMap[current] == 3) {
                    myPic.setPaint(new Color(255, 215, 0));
                }
                myPic.fillRect(746, 28, 20, 20);
                if (mapLayout[5] != 5) {
                    room[0] = true;
                }
                if (mapLayout[2] != 5) {
                    room[1] = true;
                }
                if (mapLayout[7] != 5) {
                    room[2] = true;
                }
                if (mapLayout[10] != 5) {
                    room[3] = true;
                }
            }
            if (current == 7) {
                if (masterMap[current] == 3) {
                    myPic.setPaint(new Color(255, 215, 0));
                }
                myPic.fillRect(770, 28, 20, 20);
                if (mapLayout[6] != 5) {
                    room[0] = true;
                }
                if (mapLayout[3] != 5) {
                    room[1] = true;
                }
                if (mapLayout[11] != 5) {
                    room[3] = true;
                }
            }

            if (current == 8) {
                if (masterMap[current] == 3) {
                    myPic.setPaint(new Color(255, 215, 0));
                }
                myPic.fillRect(698, 52, 20, 20);
                if (mapLayout[4] != 5) {
                    room[1] = true;
                }
                if (mapLayout[9] != 5) {
                    room[2] = true;
                }
                if (mapLayout[12] != 5) {
                    room[3] = true;
                }
            }
            if (current == 9) {
                if (masterMap[current] == 3) {
                    myPic.setPaint(new Color(255, 215, 0));
                }
                myPic.fillRect(722, 52, 20, 20);
                if (mapLayout[8] != 5) {
                    room[0] = true;
                }
                if (mapLayout[5] != 5) {
                    room[1] = true;
                }
                if (mapLayout[10] != 5) {
                    room[2] = true;
                }
                if (mapLayout[13] != 5) {
                    room[3] = true;
                }
            }
            if (current == 10) {
                if (masterMap[current] == 3) {
                    myPic.setPaint(new Color(255, 215, 0));
                }
                myPic.fillRect(746, 52, 20, 20);
                if (mapLayout[9] != 5) {
                    room[0] = true;
                }
                if (mapLayout[6] != 5) {
                    room[1] = true;
                }
                if (mapLayout[11] != 5) {
                    room[2] = true;
                }
                if (mapLayout[14] != 5) {
                    room[3] = true;
                }
            }
            if (current == 11) {
                if (masterMap[current] == 3) {
                    myPic.setPaint(new Color(255, 215, 0));
                }
                myPic.fillRect(770, 52, 20, 20);
                if (mapLayout[10] != 5) {
                    room[0] = true;
                }
                if (mapLayout[7] != 5) {
                    room[1] = true;
                }
                if (mapLayout[15] != 5) {
                    room[3] = true;
                }
            }

            if (current == 12) {
                if (masterMap[current] == 3) {
                    myPic.setPaint(new Color(255, 215, 0));
                }
                myPic.fillRect(698, 76, 20, 20);
                if (mapLayout[8] != 5) {
                    room[1] = true;
                }
                if (mapLayout[13] != 5) {
                    room[2] = true;
                }
            }
            if (current == 13) {
                if (masterMap[current] == 3) {
                    myPic.setPaint(new Color(255, 215, 0));
                }
                myPic.fillRect(722, 76, 20, 20);
                if (mapLayout[12] != 5) {
                    room[0] = true;
                }
                if (mapLayout[9] != 5) {
                    room[1] = true;
                }
                if (mapLayout[14] != 5) {
                    room[2] = true;
                }
            }
            if (current == 14) {
                if (masterMap[current] == 3) {
                    myPic.setPaint(new Color(255, 215, 0));
                }
                myPic.fillRect(746, 76, 20, 20);
                if (mapLayout[13] != 5) {
                    room[0] = true;
                }
                if (mapLayout[10] != 5) {
                    room[1] = true;
                }
                if (mapLayout[15] != 5) {
                    room[2] = true;
                }
            }
            if (current == 15) {
                if (masterMap[current] == 3) {
                    myPic.setPaint(new Color(255, 215, 0));
                }
                myPic.fillRect(770, 76, 20, 20);
                if (mapLayout[14] != 5) {
                    room[0] = true;
                }
                if (mapLayout[11] != 5) {
                    room[1] = true;
                }
            }
            finished = 0;
            for (int fin = 0; fin < 16; fin++) {
                if (enemy[0] == false && enemy[1] == false && enemy[2] == false && enemy[3] == false && enemy[4] == false && enemy[5] == false) {
                    if (mapLayout[fin] == 5 || mapLayout[fin] == 2 || mapLayout[fin] == 1) {        //checks if game is won
                        finished++;
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("Culminating Project");
        JApplet applet = new Culm();        //sets up the window
        f.getContentPane().add("Center", applet);
        applet.init();
        f.pack();
        f.setSize(800, 600);     //sets the window size
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //stops program if you x out the window    

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {     //checks if you hit escape       
            if (running == false) {
                running = true;     //toggles pause
            } else {
                running = false;
            }
        }
        if (dPressed == true && wPressed == true) { //checks if moving in 2 directions and makes it on an angle
            if (x < 722) {
                repaint();
                x += speed;
            }
            if (y > 0) {
                repaint();
                y -= speed;
            }
        } else if (dPressed == true && sPressed == true) {
            if (x < 722) {
                repaint();
                x += speed;
            }
            if (y < 453) {
                repaint();
                y += speed;
            }
        } else if (aPressed == true && wPressed == true) {
            if (x > 0) {
                repaint();
                x -= speed;
            }
            if (y > 0) {
                repaint();
                y -= speed;
            }
        } else if (aPressed == true && sPressed == true) {
            if (x > 0) {
                repaint();
                x -= speed;
            }
            if (y < 453) {
                repaint();
                y += speed;
            }
        } else {
            if (e.getKeyCode() == KeyEvent.VK_D) {  //checks if d pressed
                dPressed = true;
                if (x < 722 && wPressed == false && sPressed == false) {
                    if (y > 200 && y < 367 && x > 674) { //checks if your in the door
                        if (enemy[0] == false && enemy[1] == false && enemy[2] == false && enemy[3] == false && enemy[4] == false && enemy[5] == false) {     //checks if room is over
                            if (current == 0) {      //checks your current room
                                if (mapLayout[1] != 5) {
                                    mapLayout[0] = 2;    //sets room type
                                    current = 1;    //sets current room
                                    x = 27;
                                    if (mapLayout[1] != 2) {
                                        enemies();      //adds enimes to new room
                                    }
                                    dPressed = false;
                                }
                            } else if (current == 1) {
                                if (mapLayout[2] != 5) {
                                    mapLayout[1] = 2;
                                    current = 2;
                                    x = 27;
                                    if (mapLayout[2] != 2) {
                                        enemies();
                                    }
                                    dPressed = false;
                                }
                            } else if (current == 2) {
                                if (mapLayout[3] != 5) {
                                    mapLayout[2] = 2;
                                    current = 3;
                                    x = 27;
                                    if (mapLayout[3] != 2) {
                                        enemies();
                                    }
                                    dPressed = false;
                                }
                            } else if (current == 4) {
                                if (mapLayout[5] != 5) {
                                    mapLayout[4] = 2;
                                    current = 5;
                                    x = 27;
                                    if (mapLayout[5] != 2) {
                                        enemies();
                                    }
                                    dPressed = false;
                                }
                            } else if (current == 5) {
                                if (mapLayout[6] != 5) {
                                    mapLayout[5] = 2;
                                    current = 6;
                                    x = 27;
                                    if (mapLayout[6] != 2) {
                                        enemies();
                                    }
                                    dPressed = false;
                                }
                            } else if (current == 6) {
                                if (mapLayout[7] != 5) {
                                    mapLayout[6] = 2;
                                    current = 7;
                                    x = 27;
                                    if (mapLayout[7] != 2) {
                                        enemies();
                                    }
                                    dPressed = false;
                                }
                            } else if (current == 8) {
                                if (mapLayout[9] != 5) {
                                    mapLayout[8] = 2;
                                    current = 9;
                                    x = 27;
                                    if (mapLayout[9] != 2) {
                                        enemies();
                                    }
                                    dPressed = false;
                                }
                            } else if (current == 9) {
                                if (mapLayout[10] != 5) {
                                    mapLayout[9] = 2;
                                    current = 10;
                                    x = 27;
                                    if (mapLayout[10] != 2) {
                                        enemies();
                                    }
                                    dPressed = false;
                                }
                            } else if (current == 10) {
                                if (mapLayout[11] != 5) {
                                    mapLayout[10] = 2;
                                    current = 11;
                                    x = 27;
                                    if (mapLayout[11] != 2) {
                                        enemies();
                                    }
                                    dPressed = false;
                                }
                            } else if (current == 12) {
                                if (mapLayout[13] != 5) {
                                    mapLayout[12] = 2;
                                    current = 13;
                                    x = 27;
                                    if (mapLayout[13] != 2) {
                                        enemies();
                                    }
                                    dPressed = false;
                                }
                            } else if (current == 13) {
                                if (mapLayout[14] != 5) {
                                    mapLayout[13] = 2;
                                    current = 14;
                                    x = 27;
                                    if (mapLayout[14] != 2) {
                                        enemies();
                                    }
                                    dPressed = false;
                                }
                            } else if (current == 14) {
                                if (mapLayout[15] != 5) {
                                    mapLayout[14] = 2;
                                    current = 15;
                                    x = 27;
                                    if (mapLayout[15] != 2) {
                                        enemies();
                                    }
                                    dPressed = false;
                                }
                            }

                        }
                    }
                }
                if (x < 724) {  //if your not leaving the screen move
                    repaint();
                    x += speed;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {        //check if a is pressed
                aPressed = true && wPressed == false && sPressed == false;
                if (y > 200 && y < 367 && x < 25) {
                    if (enemy[0] == false && enemy[1] == false && enemy[2] == false && enemy[3] == false && enemy[4] == false && enemy[5] == false) {
                        if (current == 1) {
                            if (mapLayout[0] != 5) {
                                mapLayout[1] = 2;
                                current = 0;
                                x = 700;
                                if (mapLayout[0] != 2) {
                                    enemies();
                                }
                                aPressed = false;
                            }
                        } else if (current == 2) {
                            if (mapLayout[1] != 5) {
                                mapLayout[2] = 2;
                                current = 1;
                                x = 700;
                                if (mapLayout[1] != 2) {
                                    enemies();
                                }
                                aPressed = false;
                            }
                        } else if (current == 3) {
                            if (mapLayout[2] != 5) {
                                mapLayout[3] = 2;
                                current = 2;
                                x = 700;
                                if (mapLayout[2] != 2) {
                                    enemies();
                                }
                                aPressed = false;
                            }
                        } else if (current == 5) {
                            if (mapLayout[4] != 5) {
                                mapLayout[5] = 2;
                                current = 4;
                                x = 700;
                                if (mapLayout[4] != 2) {
                                    enemies();
                                }
                                aPressed = false;
                            }
                        } else if (current == 6) {
                            if (mapLayout[5] != 5) {
                                mapLayout[6] = 2;
                                current = 5;
                                x = 700;
                                if (mapLayout[5] != 2) {
                                    enemies();
                                }
                                aPressed = false;
                            }
                        } else if (current == 7) {
                            if (mapLayout[6] != 5) {
                                mapLayout[7] = 2;
                                current = 6;
                                x = 700;
                                if (mapLayout[6] != 2) {
                                    enemies();
                                }
                                aPressed = false;
                            }
                        } else if (current == 9) {
                            if (mapLayout[8] != 5) {
                                mapLayout[9] = 2;
                                current = 8;
                                x = 700;
                                if (mapLayout[8] != 2) {
                                    enemies();
                                }
                                aPressed = false;
                            }
                        } else if (current == 10) {
                            if (mapLayout[9] != 5) {
                                mapLayout[10] = 2;
                                current = 9;
                                x = 700;
                                if (mapLayout[9] != 2) {
                                    enemies();
                                }
                                aPressed = false;
                            }
                        } else if (current == 11) {
                            if (mapLayout[10] != 5) {
                                mapLayout[11] = 2;
                                current = 10;
                                x = 700;
                                if (mapLayout[10] != 2) {
                                    enemies();
                                }
                                aPressed = false;
                            }
                        } else if (current == 13) {
                            if (mapLayout[12] != 5) {
                                mapLayout[13] = 2;
                                current = 12;
                                x = 700;
                                if (mapLayout[12] != 2) {
                                    enemies();
                                }
                                aPressed = false;
                            }
                        } else if (current == 14) {
                            if (mapLayout[13] != 5) {
                                mapLayout[14] = 2;
                                current = 13;
                                x = 700;
                                if (mapLayout[13] != 2) {
                                    enemies();
                                }
                                aPressed = false;
                            }
                        } else if (current == 15) {
                            if (mapLayout[14] != 5) {
                                mapLayout[15] = 2;
                                current = 14;
                                x = 700;
                                if (mapLayout[14] != 2) {
                                    enemies();
                                }
                                aPressed = false;
                            }
                        }
                    }
                }
                if (x > 0) {
                    repaint();
                    x -= speed;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_W) {        //check if w is presesed
                wPressed = true;
                if (x > 313 && x < 480 && y < 25) {
                    if (enemy[0] == false && enemy[1] == false && enemy[2] == false && enemy[3] == false && enemy[4] == false && enemy[5] == false) {
                        if (current == 4) {
                            if (mapLayout[0] != 5) {
                                mapLayout[4] = 2;
                                current = 0;
                                y = 420;
                                if (mapLayout[0] != 2) {
                                    enemies();
                                }
                                wPressed = false;
                            }
                        } else if (current == 5) {
                            if (mapLayout[1] != 5) {
                                mapLayout[5] = 2;
                                current = 1;
                                y = 420;
                                if (mapLayout[1] != 2) {
                                    enemies();
                                }
                                wPressed = false;
                            }
                        } else if (current == 6) {
                            if (mapLayout[2] != 5) {
                                mapLayout[6] = 2;
                                current = 2;
                                y = 420;
                                if (mapLayout[2] != 2) {
                                    enemies();
                                }
                                wPressed = false;
                            }
                        } else if (current == 7) {
                            if (mapLayout[3] != 5) {
                                mapLayout[7] = 2;
                                current = 3;
                                y = 420;
                                if (mapLayout[3] != 2) {
                                    enemies();
                                }
                                wPressed = false;
                            }
                        } else if (current == 8) {
                            if (mapLayout[4] != 5) {
                                mapLayout[8] = 2;
                                current = 4;
                                y = 420;
                                if (mapLayout[4] != 2) {
                                    enemies();
                                }
                                wPressed = false;
                            }
                        } else if (current == 9) {
                            if (mapLayout[5] != 5) {
                                mapLayout[9] = 2;
                                current = 5;
                                y = 420;
                                if (mapLayout[5] != 2) {
                                    enemies();
                                }
                                wPressed = false;
                            }
                        } else if (current == 10) {
                            if (mapLayout[6] != 5) {
                                mapLayout[10] = 2;
                                current = 6;
                                y = 420;
                                if (mapLayout[6] != 2) {
                                    enemies();
                                }
                                wPressed = false;
                            }
                        } else if (current == 11) {
                            if (mapLayout[7] != 5) {
                                mapLayout[11] = 2;
                                current = 7;
                                y = 420;
                                if (mapLayout[7] != 2) {
                                    enemies();
                                }
                                wPressed = false;
                            }
                        } else if (current == 12) {
                            if (mapLayout[8] != 5) {
                                mapLayout[12] = 2;
                                current = 8;
                                y = 420;
                                if (mapLayout[8] != 2) {
                                    enemies();
                                }
                                wPressed = false;
                            }
                        } else if (current == 13) {
                            if (mapLayout[9] != 5) {
                                mapLayout[13] = 2;
                                current = 9;
                                y = 420;
                                if (mapLayout[9] != 2) {
                                    enemies();
                                }
                                wPressed = false;
                            }
                        } else if (current == 14) {
                            if (mapLayout[10] != 5) {
                                mapLayout[14] = 2;
                                current = 10;
                                y = 420;
                                if (mapLayout[10] != 2) {
                                    enemies();
                                }
                                wPressed = false;
                            }
                        } else if (current == 15) {
                            if (mapLayout[11] != 5) {
                                mapLayout[15] = 2;
                                current = 11;
                                y = 420;
                                if (mapLayout[11] != 2) {
                                    enemies();
                                }
                                wPressed = false;
                            }
                        }
                    }
                }
                if (y > 0 && aPressed == false && dPressed == false) {
                    repaint();
                    y -= speed;
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {        //check if s is pressed
                sPressed = true;
                if (x > 313 && x < 480 && y > 422) {
                    if (enemy[0] == false && enemy[1] == false && enemy[2] == false && enemy[3] == false && enemy[4] == false && enemy[5] == false) {
                        if (current == 0) {
                            if (mapLayout[4] != 5) {
                                mapLayout[0] = 2;
                                current = 4;
                                y = 27;
                                if (mapLayout[4] != 2) {
                                    enemies();
                                }
                                sPressed = false;
                            }
                        } else if (current == 1) {
                            if (mapLayout[5] != 5) {
                                mapLayout[1] = 2;
                                current = 5;
                                y = 27;
                                if (mapLayout[5] != 2) {
                                    enemies();
                                }
                                sPressed = false;
                            }
                        } else if (current == 2) {
                            if (mapLayout[6] != 5) {
                                mapLayout[2] = 2;
                                current = 6;
                                y = 27;
                                if (mapLayout[6] != 2) {
                                    enemies();
                                }
                                sPressed = false;
                            }
                        } else if (current == 3) {
                            if (mapLayout[7] != 5) {
                                mapLayout[3] = 2;
                                current = 7;
                                y = 27;
                                if (mapLayout[7] != 2) {
                                    enemies();
                                }
                                sPressed = false;
                            }
                        } else if (current == 4) {
                            if (mapLayout[8] != 5) {
                                mapLayout[4] = 2;
                                current = 8;
                                y = 27;
                                if (mapLayout[8] != 2) {
                                    enemies();
                                }
                                sPressed = false;
                            }
                        } else if (current == 5) {
                            if (mapLayout[9] != 5) {
                                mapLayout[5] = 2;
                                current = 9;
                                y = 27;
                                if (mapLayout[9] != 2) {
                                    enemies();
                                }
                                sPressed = false;
                            }
                        } else if (current == 6) {
                            if (mapLayout[10] != 5) {
                                mapLayout[6] = 2;
                                current = 10;
                                y = 27;
                                if (mapLayout[10] != 2) {
                                    enemies();
                                }
                                sPressed = false;
                            }
                        } else if (current == 7) {
                            if (mapLayout[11] != 5) {
                                mapLayout[7] = 2;
                                current = 11;
                                y = 27;
                                if (mapLayout[11] != 2) {
                                    enemies();
                                }
                                sPressed = false;
                            }
                        } else if (current == 8) {
                            if (mapLayout[12] != 5) {
                                mapLayout[8] = 2;
                                current = 12;
                                y = 27;
                                if (mapLayout[12] != 2) {
                                    enemies();
                                }
                                sPressed = false;
                            }
                        } else if (current == 9) {
                            if (mapLayout[13] != 5) {
                                mapLayout[9] = 2;
                                current = 13;
                                y = 27;
                                if (mapLayout[13] != 2) {
                                    enemies();
                                }
                                sPressed = false;
                            }
                        } else if (current == 10) {
                            if (mapLayout[14] != 5) {
                                mapLayout[10] = 2;
                                current = 14;
                                y = 27;
                                if (mapLayout[14] != 2) {
                                    enemies();
                                }
                                sPressed = false;
                            }
                        } else if (current == 11) {
                            if (mapLayout[15] != 5) {
                                mapLayout[11] = 2;
                                current = 15;
                                y = 27;
                                if (mapLayout[15] != 2) {
                                    enemies();
                                }
                                sPressed = false;
                            }
                        }

                    }
                }
                if (y < 453 && aPressed == false && dPressed == false) {
                    repaint();
                    y += speed;
                }
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            aRight = 1;            //sets if right is pressed
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            aRight = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            aUp = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            aUp = 0;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            aRight = 2;            //sets right to not being used
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            aRight = 2;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            aUp = 2;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            aUp = 2;
        }
        if (e.getKeyCode() == KeyEvent.VK_W) {
            wPressed = false;     //sets w not being used       
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            aPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            sPressed = false;
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            dPressed = false;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        requestFocus();
    }

    public void init() {
    }
}
