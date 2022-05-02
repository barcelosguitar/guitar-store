package br.com.barcelos_projects.repository;

import java.util.List;

public interface GlobalDAO <Entity>{
	
	public void add(Entity entity);
	public Entity findById(Long id);
	public void delete(Long id);
	public void update(Entity entity);
	public List<Entity> listAll();
}