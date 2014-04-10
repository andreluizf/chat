--
-- PostgreSQL database dump
--

-- Dumped from database version 8.4.17
-- Dumped by pg_dump version 9.3.1
-- Started on 2014-04-10 10:28:49

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'LATIN1';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

DROP DATABASE "webChat";
--
-- TOC entry 1794 (class 1262 OID 25189)
-- Name: webChat; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "webChat" WITH TEMPLATE = template0 ENCODING = 'LATIN1' LC_COLLATE = 'pt_PT' LC_CTYPE = 'pt_PT';


ALTER DATABASE "webChat" OWNER TO postgres;

\connect "webChat"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'LATIN1';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 3 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 1795 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 143 (class 1259 OID 25200)
-- Name: message; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE message (
    id integer NOT NULL,
    to_user integer,
    from_user integer,
    message text
);


ALTER TABLE public.message OWNER TO postgres;

--
-- TOC entry 142 (class 1259 OID 25198)
-- Name: message_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE message_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.message_id_seq OWNER TO postgres;

--
-- TOC entry 1797 (class 0 OID 0)
-- Dependencies: 142
-- Name: message_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE message_id_seq OWNED BY message.id;


--
-- TOC entry 141 (class 1259 OID 25192)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuario (
    id integer NOT NULL,
    nome character varying(255),
    apelido character varying(255),
    email character varying(255),
    data_nascimento date,
    cidade character varying(200),
    frase character varying(255)
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 140 (class 1259 OID 25190)
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_id_seq OWNER TO postgres;

--
-- TOC entry 1798 (class 0 OID 0)
-- Dependencies: 140
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE user_id_seq OWNED BY usuario.id;


--
-- TOC entry 1693 (class 2604 OID 25203)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY message ALTER COLUMN id SET DEFAULT nextval('message_id_seq'::regclass);


--
-- TOC entry 1692 (class 2604 OID 25195)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario ALTER COLUMN id SET DEFAULT nextval('user_id_seq'::regclass);


--
-- TOC entry 1789 (class 0 OID 25200)
-- Dependencies: 143
-- Data for Name: message; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 1799 (class 0 OID 0)
-- Dependencies: 142
-- Name: message_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('message_id_seq', 1, false);


--
-- TOC entry 1800 (class 0 OID 0)
-- Dependencies: 140
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_id_seq', 3, true);


--
-- TOC entry 1787 (class 0 OID 25192)
-- Dependencies: 141
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO usuario (id, nome, apelido, email, data_nascimento, cidade, frase) VALUES (2, 'asdas', 'sadsa', 'sadsa', '2014-04-01', 'asdsa', 'asdsa');
INSERT INTO usuario (id, nome, apelido, email, data_nascimento, cidade, frase) VALUES (3, 'fssdf', 'sdfsd', 'sdfs', '2014-04-02', 'sdfsd', 'sdfsd');


--
-- TOC entry 1697 (class 2606 OID 25208)
-- Name: message_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY message
    ADD CONSTRAINT message_pkey PRIMARY KEY (id);


--
-- TOC entry 1695 (class 2606 OID 25197)
-- Name: user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 1699 (class 2606 OID 25214)
-- Name: message_from_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY message
    ADD CONSTRAINT message_from_user_fkey FOREIGN KEY (from_user) REFERENCES usuario(id);


--
-- TOC entry 1698 (class 2606 OID 25209)
-- Name: message_to_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY message
    ADD CONSTRAINT message_to_user_fkey FOREIGN KEY (to_user) REFERENCES usuario(id);


--
-- TOC entry 1796 (class 0 OID 0)
-- Dependencies: 3
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-04-10 10:28:49

--
-- PostgreSQL database dump complete
--

