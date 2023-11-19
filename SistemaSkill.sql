-- DROP TABLE public.tb_skill;
-- DROP TABLE public.user_skill;
-- DROP TABLE public.tb_user;

CREATE TABLE public.tb_skill (
	skill_cd_id uuid NOT NULL,
	skill_tx_description varchar(255) NULL,
	skill_tx_name varchar(255) NULL,
	skill_blob_image bytea NULL,
	CONSTRAINT tb_skill_pkey PRIMARY KEY (skill_cd_id)
);

CREATE TABLE public.tb_user (
	usr_cd_id uuid NOT NULL,
	"role" varchar(255) NULL,
	usr_tx_login varchar(255) NULL,
	usr_tx_password varchar(255) NULL,
	CONSTRAINT tb_user_pkey PRIMARY KEY (usr_cd_id),
	CONSTRAINT tb_user_role_check CHECK (((role)::text = ANY ((ARRAY['ADMIN'::character varying, 'USER'::character varying])::text[])))
);

CREATE TABLE public.user_skill (
	usr_skill_cd_level int4 NULL,
	fk_skill_cd_id uuid NOT NULL,
	fk_usr_cd_id uuid NOT NULL,
	CONSTRAINT user_skill_pkey PRIMARY KEY (fk_skill_cd_id, fk_usr_cd_id),
	CONSTRAINT fkobdv8w1bakd2mibdmc0e6eh4g FOREIGN KEY (fk_skill_cd_id) REFERENCES public.tb_skill(skill_cd_id),
	CONSTRAINT fkqn7qm1c3rwu5102ynol2grcm6 FOREIGN KEY (fk_usr_cd_id) REFERENCES public.tb_user(usr_cd_id)
);