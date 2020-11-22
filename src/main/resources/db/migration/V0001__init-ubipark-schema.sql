
CREATE TABLE charge_point (
	id BIGSERIAL NOT NULL
		CONSTRAINT pk_charge_point PRIMARY KEY,

	name VARCHAR(126) NOT NULL,
	status VARCHAR(126) NOT NULL,
	charge_speed VARCHAR(126) NULL,

	CONSTRAINT charge_point_chk_ CHECK ((status = 'OCCUPIED') = (charge_speed IS NOT NULL))
);
