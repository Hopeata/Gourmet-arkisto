CREATE TABLE kayttaja (
    id serial primary key,
    tunnus varchar unique not null,
    sahkoposti varchar,
    salasana varchar not null,
    admin_oikeudet boolean not null,
    vip_oikeudet boolean not null
);

CREATE TABLE ruokalaji (
    id serial primary key,
    nimi varchar not null
);

CREATE TABLE paaraaka_aine (
    id serial primary key,
    nimi varchar not null
);

CREATE TABLE resepti (
    id serial primary key,
    lisaysaika timestamp not null,
    ohje varchar not null,
    kuva_url varchar,
    kayttaja_id int references kayttaja(id) not null,
    paaraaka_aine_id int references paaraaka_aine(id)
);

CREATE TABLE reseptinruokalaji (
    ruokalaji_id int references ruokalaji(id) not null,
    resepti_id int references resepti(id) not null
);

CREATE TABLE reseptinnimi (
    resepti_id int references resepti(id) not null,
    nimi varchar not null,
    on_paanimi boolean not null
);

CREATE TABLE ainesosa (
    id serial primary key,
    nimi varchar not null
);

CREATE TABLE reseptinainesosa (
    resepti_id int references resepti(id) not null,
    ainesosa_id int references ainesosa(id) not null,
    maara varchar
);
    
