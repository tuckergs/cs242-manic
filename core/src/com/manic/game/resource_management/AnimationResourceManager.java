package com.manic.game.resource_management;

import java.io.IOException;
import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.manic.game.Manic;
import com.manic.game.ObjectTimeline;
import com.manic.game.exceptions.InvalidXMLException;
import com.manic.game.helper.FileStuff;
import com.manic.game.xml.AnimationDataParser;

public class AnimationResourceManager extends ResourceManager{

	
	private HashMap < String , ObjectTimeline<TextureRegion> > animations;
	
	
	
	public AnimationResourceManager()
	{
		
		animations = new HashMap < String , ObjectTimeline<TextureRegion> >();
		
	}
	
	
	
	@Override
	public void load(String file, String name) 
					throws IOException , InvalidXMLException{
		
		
		//Get stuff from file
		String xml = FileStuff.fileToString(file);
		
		//Make new parser
		AnimationDataParser p = new AnimationDataParser();
		
		//Parse
		p.parse(xml);
		
		
		//Create ObjectTimeline
		
		ObjectTimeline <TextureRegion> anim =
				new ObjectTimeline <TextureRegion> (
						p.get_data() , p.get_length() , Manic.STEP ,
						p.is_looping() , p.is_get_on_no_keyframe());
		
		
		animations.put( name , anim );
		
		
		
	}

	@Override
	public ObjectTimeline<TextureRegion> get(String key) {

		return animations.get(key);
		
	}

}
