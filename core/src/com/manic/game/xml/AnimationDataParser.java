package com.manic.game.xml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.manic.game.exceptions.InvalidXMLException;

/**
 * 
 * @class AnimationDataParser
 * 
 * @brief A parser that parses data about animations
 * 
 * It takes an XML file and creates an ObjectTimeline of TextureRegions
 * You can get the resulting timeline from a getter
 * 
 * @author Gabe Tucker
 *
 * @contact gst06@roadrunner.com
 *
 */
public class AnimationDataParser extends XMLParser{


	//TODO: Figure out if whether we want ObjTmline here
	private HashMap <Integer , TextureRegion> _hsh;
	
	private int animation_length;
	
	private boolean loop;
	private boolean get_on_no_keyframe;
	
	
	
	@Override
	public void parse ( String xml ) throws InvalidXMLException
	{
		
		
		cur = document.parse(xml);
		
		
		String head;
		
		//Check for root node
		try{
			
			head = cur.getName();
		
		}
		catch ( NullPointerException e ){
			
			InvalidXMLException f = new InvalidXMLException();
			
			f.setCantParseXML(xml);
			
			throw f;
			
		}
		
		
		//Check for root node's name
		if ( !head.equals("animation") ){
			InvalidXMLException e = new InvalidXMLException();
			e.setInvalidTag( "animation" , head );
			throw e;
		}
		
		String err_string = "";
		
		
		//TODO: Add null pointer exceptions
		
		String frame_string = "";
		String spritepath = "";
		int cut_height;
		int cut_width;
		
		
		try {
			
			err_string = "frames";
			frame_string = cur.getChildByName("frames").getText();
			
			err_string = "path";
			spritepath = cur.getChildByName("path").getText();
			
			err_string = "height";
			cut_height = Integer.parseInt( cur.getChildByName("height").getText() );
			
			err_string = "width";
			cut_width = Integer.parseInt( cur.getChildByName("width").getText() );
			
			err_string = "length";
			animation_length = Integer.parseInt (
					cur.getChildByName("length").getText() );
		
		} catch (NullPointerException e) {
			
			InvalidXMLException f = new InvalidXMLException ();
			f.setMissingTag(err_string);
			throw f;
			
		}
		
		
		//Now see if "loop" and "get_on_no_keyframe" exist
		loop = true;
		get_on_no_keyframe = true;
		
		Element loopChild = cur.getChildByName("loop");
		Element getOnNoKeyframeChild = cur.getChildByName("get_on_no_keyframe");
		
		
		//Set these booleans if these tags are found
		if ( loopChild != null )
			loop = Boolean.parseBoolean(loopChild.getText());
		
		if ( getOnNoKeyframeChild != null )
			get_on_no_keyframe = Boolean.parseBoolean(getOnNoKeyframeChild.getText());
				
		
		
		
		
		
		
		
		//Break up frame_string
		StringTokenizer tokizer = new StringTokenizer ( frame_string , " ");
		LinkedList<Integer> lstFrameIndices = new LinkedList<Integer>();
		
		while (tokizer.hasMoreTokens())
			lstFrameIndices.addLast(
					Integer.parseInt( tokizer.nextToken() ));
		
		
		
		
		
		
		//--Do texture cutting stuff--
		
		Texture tex = new Texture(Gdx.files.internal(spritepath));
		
		TextureRegion[][] sprites = TextureRegion.split(tex, cut_width, cut_height);
		
		int firstLength = sprites.length;
		int secondLength = sprites[0].length;
		
		Iterator<Integer> itr = lstFrameIndices.listIterator();
		
		_hsh = new HashMap <Integer , TextureRegion>();
		
		for ( int i = 0 ; i < firstLength ; ++i )
		{
			for ( int j = 0 ; j < secondLength && itr.hasNext() ; ++j ){
				
				int index = (Integer) itr.next();
				
				TextureRegion reg = sprites[i][j];
				
				_hsh.put(index, reg);
				
			}
		}	
		
	}
	
	
	public HashMap <Integer , TextureRegion> get_data()
	{
		
		return _hsh;
		
	}
	
	
	public int get_length()
	{
		
		return animation_length;
		
	}

	public boolean is_looping()
	{
		return loop;
	}
	
	public boolean is_get_on_no_keyframe()
	{
		return get_on_no_keyframe;
	}
	
	
	
}
