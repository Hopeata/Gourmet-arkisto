
-- admin-käyttäjätunnuksen lisäys, tunnus admin, salasana admin
INSERT INTO kayttaja(tunnus, sahkoposti, salasana, admin_oikeudet, vip_oikeudet)
    VALUES ('admin', null, 'D033E22AE348AEB5660FC2140AEC35850C4DA997', true, true);

INSERT INTO ruokalaji(nimi) VALUES ('alkupala');

INSERT INTO ruokalaji(nimi) VALUES ('väli- tai iltapala');

INSERT INTO ruokalaji(nimi) VALUES ('pääruoka');

INSERT INTO ruokalaji(nimi) VALUES ('jälkiruoka');

INSERT INTO ruokalaji(nimi) VALUES ('lisuke');

INSERT INTO ruokalaji(nimi) VALUES ('juoma');


INSERT INTO paaraaka_aine(nimi) VALUES ('kana');

INSERT INTO paaraaka_aine(nimi) VALUES ('kala');

INSERT INTO paaraaka_aine(nimi) VALUES ('liha');

INSERT INTO paaraaka_aine(nimi) VALUES ('kasvis');

INSERT INTO paaraaka_aine(nimi) VALUES ('merenelävä');

