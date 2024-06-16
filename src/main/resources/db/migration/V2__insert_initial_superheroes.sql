INSERT INTO superhero (alias, name, origin) VALUES
('Captain Marvel', 'Carol Danvers', 'Exposed to Space Stone reactor overload'),
('Iron Man', 'Tony Stark', 'Kidnapped in Afghanistan, created the first iron-man suit to escape.');

INSERT INTO superhero_powers (superhero_id, powers) VALUES
((SELECT id FROM superhero WHERE alias='Captain Marvel'), 'photon-blast'),
((SELECT id FROM superhero WHERE alias='Captain Marvel'), 'flight'),
((SELECT id FROM superhero WHERE alias='Captain Marvel'), 'super-strength'),
((SELECT id FROM superhero WHERE alias='Captain Marvel'), 'healing'),
((SELECT id FROM superhero WHERE alias='Iron Man'), 'genius-intelligence'),
((SELECT id FROM superhero WHERE alias='Iron Man'), 'wealth');

INSERT INTO superhero_weapons (superhero_id, weapons) VALUES
((SELECT id FROM superhero WHERE alias='Iron Man'), 'arc-reactor'),
((SELECT id FROM superhero WHERE alias='Iron Man'), 'iron-man-suit'),
((SELECT id FROM superhero WHERE alias='Iron Man'), 'iron-legion');

INSERT INTO superhero_associations (superhero_id, associations) VALUES
((SELECT id FROM superhero WHERE alias='Captain Marvel'), 'space-stone'),
((SELECT id FROM superhero WHERE alias='Captain Marvel'), 'skrulls'),
((SELECT id FROM superhero WHERE alias='Captain Marvel'), 'photon'),
((SELECT id FROM superhero WHERE alias='Captain Marvel'), 'kree'),
((SELECT id FROM superhero WHERE alias='Captain Marvel'), 'avengers'),
((SELECT id FROM superhero WHERE alias='Iron Man'), 'war-machine'),
((SELECT id FROM superhero WHERE alias='Iron Man'), 'avengers'),
((SELECT id FROM superhero WHERE alias='Iron Man'), 'jarvis'),
((SELECT id FROM superhero WHERE alias='Iron Man'), 'thanos'),
((SELECT id FROM superhero WHERE alias='Iron Man'), 'pepper-potts');
