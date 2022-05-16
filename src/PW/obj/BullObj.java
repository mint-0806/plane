package PW.obj;

import PW.GameWin;
import PW.PW.utils.GameUtils;

import java.awt.*;

public class BullObj extends GameObj{
    public BullObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y+=speed;
        if(this.getRec().intersects(this.frame.planeObj.getRec())){
            GameWin.state = 3;
        }
        if(y>600){
            this.x=-200;
            this.y= 230;
            GameUtils.removeObjList.add(this);
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
