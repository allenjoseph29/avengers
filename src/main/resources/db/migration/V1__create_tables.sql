
CREATE TABLE superhero (
       id SERIAL PRIMARY KEY,
       alias VARCHAR(255) UNIQUE NOT NULL,
       name VARCHAR(255),
       origin TEXT
);

CREATE TABLE superhero_powers (
      superhero_id INT,
      powers VARCHAR(255),
      FOREIGN KEY (superhero_id) REFERENCES superhero(id)  ON DELETE CASCADE,
      UNIQUE (superhero_id, powers)
);

CREATE TABLE superhero_weapons (
       superhero_id INT,
       weapons VARCHAR(255),
       FOREIGN KEY (superhero_id) REFERENCES superhero(id)  ON DELETE CASCADE,
       UNIQUE (superhero_id, weapons)
);

CREATE TABLE superhero_associations (
        superhero_id INT,
        associations VARCHAR(255),
        FOREIGN KEY (superhero_id) REFERENCES superhero(id)  ON DELETE CASCADE,
        UNIQUE (superhero_id, associations)
);
