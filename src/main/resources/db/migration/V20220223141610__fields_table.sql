CREATE TABLE public.request_fields (
  id bigserial not null constraint request_fields_pkey primary key,
  paynet_id bigint,
  order_id smallint,
  name varchar(255),
  title_ru varchar(255),
  title_uz varchar(255),
  required boolean ,
  read_only boolean ,
  is_customer_id varchar(255),
  field_control varchar(255),
  service_id bigint constraint fk_request_fields_service_id references service
);