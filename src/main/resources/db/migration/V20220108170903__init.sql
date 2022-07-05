CREATE TABLE public.category
(
  id bigserial not null constraint category_pkey primary key,
  created_at timestamp,
  updated_at timestamp,
  deleted_at timestamp,
  name varchar(255),
  description varchar(255),
  icon_id varchar(255),
  paynet_category_id bigint,
  enabled boolean NOT NULL DEFAULT true
)

TABLESPACE pg_default;

CREATE TABLE public.provider
(
  id bigserial not null constraint provider_pkey primary key,
  created_at timestamp,
  updated_at timestamp,
  deleted_at timestamp,
  active boolean NOT NULL DEFAULT true,
  legal_name varchar(255),
  name varchar(255),
  icon_id varchar(255),
  payment_instrument varchar(255),
  payment_instrument_provider_id bigint,
  address_registry varchar(255),
  inn varchar(255),
  comission varchar(255),
  category_id bigint constraint fk_category_id references category
)

TABLESPACE pg_default;

CREATE TABLE public.service
(
  id bigserial not null constraint service_pkey primary key,
  created_at timestamp,
  updated_at timestamp,
  deleted_at timestamp,
  active boolean NOT NULL DEFAULT true,
  legal_name varchar(255),
  name varchar(255),
  comission varchar(255),
  min_amount bigint,
  max_amount bigint,
  fixed_price bigint,
  payment_instrument varchar(255),
  icon_id varchar(255),
  uzcard_merchant_id varchar(255),
  uzcard_terminal_id varchar(255),
  humo_merchant_id varchar(255),
  humo_terminal_id varchar(255),
  username varchar(255),
  password varchar(255),
  payment_instrument_service_id bigint,
  provider_id bigint constraint fk_provider_id references provider
)

TABLESPACE pg_default;