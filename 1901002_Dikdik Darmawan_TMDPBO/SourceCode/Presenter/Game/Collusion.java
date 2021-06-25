package Presenter.Game;
public class Collusion{
    private int floorTop,floorLeft,floorBottom,floorRight;
    private int playerTop,playerLeft,playerBottom,playerRight;
    private int object = -1;
    private PointGame point;

	public Collusion(PointGame point){
        this.point = point;
	}

    public int check(Player player, Handler handler){
        if(object == -1){
            for(int i=0;i<handler.object.size(); i++){
                setPosisition(player,handler.object.get(i));
                if(handler.object.get(i).id != ID.Floor){
                    if(checkColussionTingkatan(player,handler.object.get(i)) == 0){
                        return 0;
                    }
                    if(checkColussionTingkatan(player,handler.object.get(i)) == 1){
                        return 1;
                    }

                }
            }            
        }
        return -1;
    }
	
    public void setPosisition(Player player, ObjectParent floor){
        floorLeft = floor.x;
        floorRight = floor.x + floor.width;
        floorTop = floor.y;
        floorBottom = floor.y + floor.height;
        
        playerLeft = player.x;
        playerRight = player.x + player.width;
        playerTop = player.y;
        playerBottom = player.y + player.height;
    }

    public int checkColussionTingkatan(Player player, ObjectParent floor){

        if (floorLeft < playerRight && floorRight > playerLeft 
            && floorTop < playerBottom && floorBottom > playerTop) 
          {
            if((playerBottom >= floorTop) && (playerTop < floorTop)){
                player.y = floorTop - floor.height+5;
                player.inFloor = true;
                player.gravitasiA = false;
                if(playerLeft < floorLeft || playerRight > floorRight){
                    player.gravitasiA = true;
                }
                return 0;
            }else if((playerTop <= floorBottom) && (playerBottom > floorBottom)){
                player.gravitasiA = true;
                return 1;
            }
        }
        return -1;
	}

}