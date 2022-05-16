package PW.PW.utils;

import PW.obj.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameUtils {

    public static Image bgImg = Toolkit.getDefaultToolkit().getImage("img/bg1.jpg");
    public static Image bossImg = Toolkit.getDefaultToolkit().getImage("img/boss.png");
    public static Image explodeImg = Toolkit.getDefaultToolkit().getImage("img/explode/e6.gif");
    public static Image planeImg = Toolkit.getDefaultToolkit().getImage("img/plane.png");
    public static Image shellImg = Toolkit.getDefaultToolkit().getImage("img/bulletYellow.png");
    public static Image enemyImg = Toolkit.getDefaultToolkit().getImage("img/enemy.png");
    public static Image bullImg = Toolkit.getDefaultToolkit().getImage("img/bulletGreen.png");
    public static List<ShellObj> shellObjList =new ArrayList<>();
    public static List<GameObj> gameObjList =new ArrayList<>();
    public static List<EnemyObj> enemyObjList =new ArrayList<>();
    public static List<GameObj> removeObjList =new ArrayList<>();
    public static List<BullObj> bullObjList =new ArrayList<>();
    public static List<ExplodeObj> explodeObjList =new ArrayList<>();

    public static void drawWord(Graphics gImage,String str,Color color,int size,int x,int y){
     gImage.setColor(color);
     gImage.setFont(new Font("仿宋",Font.BOLD,size));
     gImage.drawString(str,x,y);
    }
}
