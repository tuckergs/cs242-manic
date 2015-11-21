package com.manic.game.xml;

import java.io.IOException;

import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.manic.game.Manic;
import com.manic.game.exceptions.InvalidXMLException;
import com.manic.game.helper.FileStuff;
import com.manic.game.resource_management.AnimationResourceManager;

public class AnimationResourceManagerDataParser extends XMLParser{

	
	AnimationResourceManager res;
	
	public AnimationResourceManagerDataParser()
	{
		
		super();
		
		res = Manic.res_animations;
		
	}
	
	public AnimationResourceManagerDataParser(AnimationResourceManager arm){
		
		super();
		
		res = arm;
		
	}
	
	
	@Override
	public void parse(String xml) throws InvalidXMLException {
		
		//Parse xml
		cur = document.parse(xml);
		
		String rootName;
		
		
		//Check if the XML is empty
		try{
			
			rootName = cur.getName();
			
		}
		catch (NullPointerException e){
			
			InvalidXMLException f = new InvalidXMLException();
			
			f.setCantParseXML(xml);
			
			throw f;
			
		}
		
		
		if ( !rootName.equals("animations") ){
			
			InvalidXMLException f = new InvalidXMLException();
			
			f.setInvalidTag("animations", rootName );
			
			throw f;
			
		}
		
		
		
		//Parse file
		int childCount = cur.getChildCount();
				
		
		
		for ( int i = 0 ; i < childCount ; ++i )
		{
			
			Element child = cur.getChild(i);
			
			String childName = child.getName();
			
			String err_string = "";
			
			String animationPath = "" , animationName = "";
			
			//Check if child name is correct
			if ( !child.getName().equals("ref")){
				
				InvalidXMLException f = new InvalidXMLException();
				
				f.setInvalidTag( "ref", childName );
				
			}
			
			
			//Check if the tags we need are missing
			try{
				
				err_string = "path";
				animationPath = child.getChildByName("path").getText();
				
				err_string = "name";
				animationName = child.getChildByName("name").getText();
				
				if ( animationName == null )
					animationName = "";
				
				
			}
			catch (NullPointerException e)
			{
				
				InvalidXMLException f = new InvalidXMLException();
				
				f.setMissingTag(err_string);
				
				throw f;
				
			}
			
			
			
			//Load into resource
			try {
				
				res.load(animationPath, animationName);
			
			} catch (IOException e) {
			
				InvalidXMLException f = new InvalidXMLException();
				
				f.setCantParseFile(animationPath);
				
				throw f;
				
			}
			
		}
		
		
		
		
	}

}
