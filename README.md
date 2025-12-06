-- INSERT INTO contact (usr_id, ctct_usr_id, ctct_nick_nm, fav_yn, del_yn)
-- SELECT 
--     1 AS usr_id,
--     generate_series AS ctct_usr_id,
--     CONCAT('Contact_', generate_series),
--     0,
--     0
-- FROM generate_series(1, 10000);

-- select * from contact;

-- adding groupid to ctct-grp 
-- INSERT INTO ctct_grp (
--     grp_id,
--     grp_nm,
--     grp_disp_ord_no,
--     ownr_usr_id,
--     del_yn,
--     grp_img_url,
--     grp_img_upd_utmx
-- )
-- VALUES (
--     5000,
--     'Performance_Test_Group',
--     1,
--     1,
--     0,
--     'https://dummyimage.com/100x100/000/fff&text=PerfGroup',
--     EXTRACT(EPOCH FROM NOW())
-- );

--  adding 5000 member to groupid 5000
INSERT INTO ctct_grp_memb (
    grp_id,
    usr_id,
    ctct_usr_id,
    grp_nm,
    grp_disp_ord_no,
    ownr_usr_id,
    del_yn
)
SELECT
    5000 AS grp_id,
    1 AS usr_id,
    gs AS ctct_usr_id,
    'TestGroupName' AS grp_nm,
    1 AS grp_disp_ord_no,
    1 AS ownr_usr_id,
    0 AS del_yn
FROM generate_series(1, 10000) AS gs;

-- SELECT count(*) FROM ctct_grp_memb
-- WHERE grp_id = 5000;
-- select count(*) from ctct_grp_memb;

-- select * from ctct_grp;

-- update ctct_grp as grp 
-- grp.grp_nm = "testgroup"
-- where grp.grp_id = 5000
