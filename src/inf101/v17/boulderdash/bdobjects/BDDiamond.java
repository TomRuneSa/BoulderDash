package inf101.v17.boulderdash.bdobjects;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

import java.io.InputStream;

import inf101.v17.boulderdash.maps.BDMap;

/**
 * A diamond object. All its logic is implemented in the abstract superclass.
 *
 * @author larsjaffke
 *
 */
public class BDDiamond extends AbstractBDFallingObject {

	private ImagePattern image;
	
	public BDDiamond(BDMap owner) {
		super(owner);
		InputStream resourceAsStream = getClass().getResourceAsStream("Gold.png");
		ImagePattern image = new ImagePattern(new Image(resourceAsStream), 0, 0, 1.0,1.0, true);
		this.image=image;
	}

	@Override
	public Paint getColor() {
		return image;
	}
	

}
