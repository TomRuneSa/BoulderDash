package inf101.v17.boulderdash.bdobjects;

import java.io.InputStream;

import inf101.v17.boulderdash.Direction;
import inf101.v17.boulderdash.IllegalMoveException;
import inf101.v17.boulderdash.Position;
import inf101.v17.boulderdash.maps.BDMap;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

public class BDRock extends AbstractBDFallingObject {

	private ImagePattern image;
	
		public BDRock(BDMap owner) {
		super(owner);

	}

	@Override
	public Paint getColor() {
		if(image == null){
			InputStream resourceAsStream = getClass().getResourceAsStream("grass.png");

			image = new ImagePattern(new Image(resourceAsStream), 0, 0, 1.0,1.0, true);
		}
		return image;
	}

	public boolean push(Direction dir) throws IllegalMoveException {

		if (dir != Direction.WEST && dir != Direction.EAST) {
			//Checks that the player wants to push the rock either east or west.
			throw new IllegalMoveException("You have to move the rock either EAST or WEST");
		}

		Position rock = owner.getPosition(this);
		//Gets the position of the rock.

		Position nextPos = rock.moveDirection(dir);
		//Gets the next position to the rock in the direction the player wants to move.
		
		if (owner.canGo(nextPos) && owner.get(nextPos) instanceof BDEmpty) {
			prepareMove(nextPos);
			step();
			//If the criterias of moving, and the next space is empty, move.
			return true;
		}else{
			throw new IllegalMoveException("The space " + nextPos + " is not a valid move.");
		}
		
		
		

	}

}
