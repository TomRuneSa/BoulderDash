package inf101.v17.boulderdash.bdobjects;

import inf101.v17.boulderdash.Direction;
import inf101.v17.boulderdash.IllegalMoveException;
import inf101.v17.boulderdash.Position;
import inf101.v17.boulderdash.maps.BDMap;
import javafx.scene.paint.Color;

public class BDRock extends AbstractBDFallingObject {

	public BDRock(BDMap owner) {
		super(owner);
	}

	@Override
	public Color getColor() {
		return Color.DARKGRAY;
	}

	public boolean push(Direction dir) throws IllegalMoveException {

		if (dir != Direction.WEST && dir != Direction.EAST) {
			throw new IllegalMoveException("You have to move the rock either EAST or WEST");
		}

		Position rock = owner.getPosition(this);

		nextPos = rock.moveDirection(dir);
		
		if (owner.canGo(nextPos) && owner.get(nextPos) instanceof BDEmpty) {
			prepareMove(nextPos);
			step();
		}else{
			throw new IllegalMoveException("The space " + nextPos + " is not a valid move.");
		}
		
		Position newPos = nextPos;
		if(newPos == rock){
			return false;
		}
		return true;
		

	}

}
