import java.awt.image.BufferedImage;

public class Enemy  implements Runnable{
    //存储当前坐标
    private int x,y;
    //存储敌人类型
    private int type;
    //判断敌人运动的方向
    private boolean face_to = true;
    //用于显示敌人的当前图像
    private BufferedImage show;
    //定义一个背景对象
    private BackGround bg;
    //食人花运动的极限范围
    private int max_up = 0;
    private int max_down = 0;
    //定义线程对象
    private Thread thread = new Thread(this);
    //定义当前图片的状态
    private int image_type = 0;

    //蘑菇敌人的构造函数
    public Enemy(int x,int y,boolean face_to,int type,BackGround bg){
        this.x = x;
        this.y = y;
        this.face_to = face_to;
        this.type = type;
        this.bg = bg;
        show = staticValue.mogu.get(0);
        thread.start();
    }
    //食人花敌人的构造函数
    public Enemy(int x,int y ,boolean face_to,int type,int max_up,int max_down,BackGround bg){
        this.x = x;
        this.y = y;
        this.face_to = face_to;
        this.type = type;
        this.max_up = max_up;
        this.max_down = max_down;
        this.bg = bg;
        show = staticValue.flower.get(0);
        thread.start();
    }

    //敌人死亡方法
    public void death(){
        show = staticValue.mogu.get(2);
        this.bg.getEnemyList().remove(this);
    }

    @Override
    public void run() {
        while (true){
            //判断是否是蘑菇敌人
            if (type == 1){
                if (face_to){
                    this.x -= 2;
                }else{
                    this.x += 2;
                }
                //判断他是否为1
                image_type = image_type == 1 ? 0 : 1;
                //改变当前的show
                show = staticValue.mogu.get(image_type);
            }
            //定义两个布尔变量
            boolean canLeft = true;
            boolean canRight = true;

            for (int i = 0;i < bg.getObstacleList().size();i++) {
                Obstacle ob1 = bg.getObstacleList().get(i);
                //判断是否可以右走
                if (ob1.getX() == this.x + 36 && (ob1.getY() + 65 > this.y && ob1.getY() - 35 < this.y)){
                    canRight = false;
                }
                //判断是否可以左走
                if (ob1.getX() == this.x -36 && (ob1.getY() + 65 > this.y && ob1.getY() -35 < this.y)){
                    canLeft = false;
                }
            }

            if (face_to && !canLeft || this.x == 0){
                face_to = false;
            }//如果蘑菇敌人向右走的并且已经无法走了，或者走到了边界处，那就改变方向
            else if((!face_to) &&(!canRight) || this.x == 764){
                face_to = true;
            }

            //判断是否是食人花敌人
            if (type == 2){
                if (face_to){
                    this.y -= 2;
                }else{
                    this.y += 2;
                }
                image_type = image_type == 1 ? 0 : 1;

                //食人花是否到达极限位置
                if (face_to && (this.y == max_up)){
                    face_to = false;
                }
                if ((!face_to) && (this.y == max_down)){
                    face_to = true;
                }
                show = staticValue.flower.get(image_type);
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getType() {
        return type;
    }

    public BufferedImage getShow() {
        return show;
    }

    public void setShow(BufferedImage show) {
        this.show = show;
    }
}
