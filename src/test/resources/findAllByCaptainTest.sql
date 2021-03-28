INSERT INTO public.crews (id, captain, nationality, size) VALUES (1, 'Sparrow', 'Spanish', 50);
INSERT INTO public.orders (id, client, description, order_status, reward, crew_id) VALUES (1, 'France', 'sink 5 english ships', 'DONE', 200, 1);
INSERT INTO public.orders (id, client, description, order_status, reward, crew_id) VALUES (2, 'England', 'sink 5 france ships', 'REJECTED', 150, 1);
INSERT INTO public.orders (id, client, description, order_status, reward, crew_id) VALUES (3, 'Spanish', 'sink 3 english ships', 'ANALYSIS', 100, 1);