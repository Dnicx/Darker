package render;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RenderableHolder {
	
	private static final RenderableHolder instance = new RenderableHolder();
	
	private List<Renderable> entities;
	private Comparator<Renderable> comparator;
	
	public RenderableHolder() {
		entities = new ArrayList<Renderable>();
		comparator = new Comparator<Renderable>() {
			@Override
			public int compare(Renderable o1, Renderable o2) {
				// TODO Auto-generated method stub
				if (o1.getz() > o2.getz()) return 1;
				if (o1.getz() < o2.getz()) return -1;
				return 0;
			}
		};
	
	}
	static { 
		//loadResource();
	}
	
	
	public List<Renderable> getEntity() {
		return entities;
	}
	
	public synchronized void add(Renderable e) {
		entities.add(e);
		Collections.sort(entities, comparator);
	}

	
	public static RenderableHolder getInstance() {
		return instance;
	}
	
	
}
