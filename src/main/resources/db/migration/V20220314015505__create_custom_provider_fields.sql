CREATE TABLE public.extra_service_fields (
    id bigserial not null
		constraint service_fields_pkey
			primary key,
	name varchar(255) not null,
	value varchar(255),
	provider_id bigint not null
		constraint fk_provider_fields_id
			references provider
);