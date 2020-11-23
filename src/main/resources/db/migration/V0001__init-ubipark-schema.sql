
CREATE TABLE charge_point (
	id BIGSERIAL NOT NULL
		CONSTRAINT pk_charge_point PRIMARY KEY,

	name VARCHAR(126) NOT NULL,
	status VARCHAR(126) NOT NULL,
	charge_start_date TIMESTAMP NULL,
	charge_speed VARCHAR(126) NULL,
	
	version BIGINT DEFAULT 0 NOT NULL,

	CONSTRAINT charge_point_chk_charge_start_date_null_status CHECK ((status = 'OCCUPIED') = (charge_start_date IS NOT NULL)),
	CONSTRAINT charge_point_chk_charge_speed_null_status CHECK ((status = 'OCCUPIED') = (charge_speed IS NOT NULL))
);
