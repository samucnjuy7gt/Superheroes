INSERT INTO public.universo (id,nombre) VALUES
	(1,'The Boys'),
	(2,'DC'),
	(3,'Marvel');
	
INSERT INTO public.poder (id,nombre) VALUES
	(1,'Rayos laser'),
	(2,'Invisibilidad'),
	(3,'Ser rico'),
	(4,'Volar'),
	(5,'Super fuerza');

INSERT INTO public.superheroe (id,nombre,vivo,universo_id) VALUES
	(1,'Patriota',true,1),
	(2,'Batman',true,2),
	(3,'Translucido',false,1),
	(4,'Superman',true,2),
	(5,'Soldier Boy',true,1),
	(6,'Iron Man',false,3);
	
INSERT INTO public.superheroe_universo (s_u_heroe_id,s_u_universo_id) VALUES
	(1,1),
	(2,2),
	(3,1),
	(4,2),
	(5,1),
	(6,3);
	
INSERT INTO public.superheroe_poder (s_p_heroe_id,s_p_poder_id) VALUES
	(1,1),
	(1,3),
	(1,4),
	(1,5),
	(2,3),
	(3,2),
	(4,1),
	(4,4),
	(4,5),
	(5,5),
	(6,3);