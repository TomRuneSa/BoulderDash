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
		InputStream resourceAsStream = getClass().getResourceAsStream("grass.png");
		ImagePattern image = new ImagePattern(new Image(resourceAsStream), 0, 0, 1.0,1.0, true);
		this.image=image;
	}

	@Override
	public Paint getColor() {
		return image;
	}

	public boolean push(Direction dir) throws IllegalMoveException {

		if (dir != Direction.WEST && dir != Direction.EAST) {
			throw new IllegalMoveException("You have to move the rock either EAST or WEST");
		}

		Position rock = owner.getPosition(this);

		Position nextPos = rock.moveDirection(dir);
		
		if (owner.canGo(nextPos) && owner.get(nextPos) instanceof BDEmpty) {
			prepareMove(nextPos);
			step();
			return true;
		}else{
			throw new IllegalMoveException("The space " + nextPos + " is not a valid move.");
		}
		
		
		

	}

}
