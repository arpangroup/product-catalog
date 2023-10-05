--INSERT INTO tbl_catalog (id, name) VALUES(1, 'catalog1') ON CONFLICT DO NOTHING;
--INSERT INTO tbl_category (id, name, catalog_id) VALUES(1, 'ProductCategory2', 1) ON CONFLICT DO NOTHING;
--
--DROP TABLE public.tbl_catalog CASCADE;
--DROP TABLE public.tbl_catalog_aud CASCADE;
--DROP TABLE public.tbl_category CASCADE;
--DROP TABLE public.tbl_category_aud CASCADE;
--DROP TABLE public.tbl_category_media CASCADE;
--DROP TABLE public.tbl_media CASCADE;
--DROP TABLE public.tbl_product CASCADE;
--DROP TABLE public.tbl_product_attribute CASCADE;
--DROP TABLE public.tbl_product_attribute_aud CASCADE;
--DROP TABLE public.tbl_product_aud CASCADE;
--DROP TABLE public.tbl_product_category_product CASCADE;
--DROP TABLE public.tbl_product_category_product_aud CASCADE;
--DROP TABLE public.tbl_product_media CASCADE;
--DROP TABLE public.tbl_store_catalog CASCADE;
--DROP TABLE public.tbl_store_catalog_aud CASCADE;
--DROP TABLE public.tbl_student CASCADE;
--DROP TABLE public.tbl_tax_config CASCADE;
--DROP TABLE public.tbl_tax_config_aud CASCADE;
--DROP TABLE public.tbl_tax_policy CASCADE;
--DROP TABLE public.tbl_tax_policy_aud CASCADE;
--DROP TABLE public.tbl_tax_rate CASCADE;
--DROP TABLE public.tbl_tax_rate_aud CASCADE;
--DROP TABLE public.tbl_tax_treatment CASCADE;
--DROP TABLE public.tbl_tax_treatment_aud CASCADE;
--DROP TABLE public.tbl_test CASCADE;
--DROP TABLE public.tbl_web_store CASCADE;
--DROP TABLE public.tbl_web_store_aud CASCADE;



WITH catalogId AS (
    INSERT INTO tbl_catalog (id, name) VALUES(1, 'Catalog1') ON CONFLICT DO nothing returning id
),
categoryId as (
    INSERT INTO tbl_category (id, name, catalog_id) VALUES
    (1, 'Category1', 1),
    (2, 'Category2', 1),
    (3, 'Category3', 1)
    ON CONFLICT DO NOTHING returning id
),
storeId as (
    INSERT INTO tbl_web_store(id, name, slug) values
    (1, 'Demo Store1', 'demo-store1')
    ON CONFLICT DO NOTHING returning id
),
storeCatalogRelation as (
    INSERT INTO tbl_store_catalog(id, webstore_id, catalog_id) values
    (1, (select id from storeId), (select id from catalogId))
    ON CONFLICT DO NOTHING returning id
)
select * from storeCatalogRelation;
