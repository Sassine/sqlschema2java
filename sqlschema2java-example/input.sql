CREATE TABLE hist_policy_info
	(policy_id CHAR(4) NOT NULL,
	coverage INT NOT NULL,
	bus_start VARCHAR(120) NOT NULL,
	bus_end DATE NOT NULL,
	sys_start TIMESTAMP(12) NOT NULL,
	sys_end TIMESTAMP(12) NOT NULL,
	create_id TIMESTAMP(12));