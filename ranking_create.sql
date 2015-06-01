--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.6
-- Dumped by pg_dump version 9.3.6
-- Started on 2015-06-01 15:19:56
/*
SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

DROP DATABASE ranking;
--
-- TOC entry 2002 (class 1262 OID 17724)
-- Name: ranking; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE ranking WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Polish_Poland.1250' LC_CTYPE = 'Polish_Poland.1250';


ALTER DATABASE ranking OWNER TO postgres;

\connect ranking
*/
SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 5 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--
/*
CREATE SCHEMA public;
*/

ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2003 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 184 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2005 (class 0 OID 0)
-- Dependencies: 184
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 174 (class 1259 OID 17751)
-- Name: architecture; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE architecture (
    id integer NOT NULL,
    vehicle integer NOT NULL,
    fuel integer NOT NULL,
    mean_imap double precision DEFAULT 0 NOT NULL,
    sd_factor_imap double precision DEFAULT 0 NOT NULL,
    runs integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.architecture OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 17754)
-- Name: architecture_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE architecture_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.architecture_id_seq OWNER TO postgres;

--
-- TOC entry 2006 (class 0 OID 0)
-- Dependencies: 175
-- Name: architecture_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE architecture_id_seq OWNED BY architecture.id;


--
-- TOC entry 178 (class 1259 OID 17790)
-- Name: brand; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE brand (
    id integer NOT NULL,
    name character varying(30) NOT NULL
);


ALTER TABLE public.brand OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 17793)
-- Name: brand_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE brand_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.brand_id_seq OWNER TO postgres;

--
-- TOC entry 2007 (class 0 OID 0)
-- Dependencies: 179
-- Name: brand_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE brand_id_seq OWNED BY brand.id;


--
-- TOC entry 171 (class 1259 OID 17727)
-- Name: driver; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE driver (
    id integer NOT NULL,
    nickname character varying(20) NOT NULL,
    vehicle integer NOT NULL,
    eco_points integer DEFAULT 0 NOT NULL,
    safe_points integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.driver OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 17725)
-- Name: driver_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE driver_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.driver_id_seq OWNER TO postgres;

--
-- TOC entry 2008 (class 0 OID 0)
-- Dependencies: 170
-- Name: driver_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE driver_id_seq OWNED BY driver.id;


--
-- TOC entry 182 (class 1259 OID 17820)
-- Name: fuel; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE fuel (
    id integer NOT NULL,
    name character varying(30) NOT NULL,
    ratio double precision NOT NULL,
    density double precision NOT NULL
);


ALTER TABLE public.fuel OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 17823)
-- Name: fuel_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE fuel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fuel_id_seq OWNER TO postgres;

--
-- TOC entry 2009 (class 0 OID 0)
-- Dependencies: 183
-- Name: fuel_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE fuel_id_seq OWNED BY fuel.id;


--
-- TOC entry 176 (class 1259 OID 17779)
-- Name: model; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE model (
    id integer NOT NULL,
    name character varying(50) NOT NULL,
    brand integer NOT NULL
);


ALTER TABLE public.model OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 17782)
-- Name: model_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE model_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.model_id_seq OWNER TO postgres;

--
-- TOC entry 2010 (class 0 OID 0)
-- Dependencies: 177
-- Name: model_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE model_id_seq OWNED BY model.id;


--
-- TOC entry 181 (class 1259 OID 17808)
-- Name: run; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE run (
    id integer NOT NULL,
    vehicle integer NOT NULL,
    avg_imap double precision NOT NULL,
    ending_time timestamp without time zone NOT NULL
);


ALTER TABLE public.run OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 17806)
-- Name: run_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE run_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.run_id_seq OWNER TO postgres;

--
-- TOC entry 2011 (class 0 OID 0)
-- Dependencies: 180
-- Name: run_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE run_id_seq OWNED BY run.id;


--
-- TOC entry 172 (class 1259 OID 17735)
-- Name: vehicle; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE vehicle (
    id integer NOT NULL,
    model integer NOT NULL,
    mass double precision NOT NULL,
    engine_volume double precision NOT NULL,
    power double precision NOT NULL
);


ALTER TABLE public.vehicle OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 17738)
-- Name: vehicle_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE vehicle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.vehicle_id_seq OWNER TO postgres;

--
-- TOC entry 2012 (class 0 OID 0)
-- Dependencies: 173
-- Name: vehicle_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE vehicle_id_seq OWNED BY vehicle.id;


--
-- TOC entry 1863 (class 2604 OID 17756)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY architecture ALTER COLUMN id SET DEFAULT nextval('architecture_id_seq'::regclass);


--
-- TOC entry 1868 (class 2604 OID 17795)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY brand ALTER COLUMN id SET DEFAULT nextval('brand_id_seq'::regclass);


--
-- TOC entry 1859 (class 2604 OID 17730)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY driver ALTER COLUMN id SET DEFAULT nextval('driver_id_seq'::regclass);


--
-- TOC entry 1870 (class 2604 OID 17825)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fuel ALTER COLUMN id SET DEFAULT nextval('fuel_id_seq'::regclass);


--
-- TOC entry 1867 (class 2604 OID 17784)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY model ALTER COLUMN id SET DEFAULT nextval('model_id_seq'::regclass);


--
-- TOC entry 1869 (class 2604 OID 17811)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY run ALTER COLUMN id SET DEFAULT nextval('run_id_seq'::regclass);


--
-- TOC entry 1862 (class 2604 OID 17740)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vehicle ALTER COLUMN id SET DEFAULT nextval('vehicle_id_seq'::regclass);


--
-- TOC entry 1876 (class 2606 OID 17773)
-- Name: architecture_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY architecture
    ADD CONSTRAINT architecture_pkey PRIMARY KEY (id);


--
-- TOC entry 1880 (class 2606 OID 17800)
-- Name: brand_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY brand
    ADD CONSTRAINT brand_pkey PRIMARY KEY (id);


--
-- TOC entry 1872 (class 2606 OID 17734)
-- Name: driver_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY driver
    ADD CONSTRAINT driver_pkey PRIMARY KEY (id);


--
-- TOC entry 1884 (class 2606 OID 17830)
-- Name: fuel_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY fuel
    ADD CONSTRAINT fuel_pkey PRIMARY KEY (id);


--
-- TOC entry 1878 (class 2606 OID 17789)
-- Name: model_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY model
    ADD CONSTRAINT model_pkey PRIMARY KEY (id);


--
-- TOC entry 1882 (class 2606 OID 17813)
-- Name: run_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY run
    ADD CONSTRAINT run_pkey PRIMARY KEY (id);


--
-- TOC entry 1874 (class 2606 OID 17745)
-- Name: vehicle_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY vehicle
    ADD CONSTRAINT vehicle_pkey PRIMARY KEY (id);


--
-- TOC entry 1888 (class 2606 OID 17836)
-- Name: architecture_fuel_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY architecture
    ADD CONSTRAINT architecture_fuel_fkey FOREIGN KEY (fuel) REFERENCES fuel(id);


--
-- TOC entry 1887 (class 2606 OID 17774)
-- Name: architecture_vehicle_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY architecture
    ADD CONSTRAINT architecture_vehicle_fkey FOREIGN KEY (vehicle) REFERENCES vehicle(id);


--
-- TOC entry 1885 (class 2606 OID 17746)
-- Name: driver_vehicle_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY driver
    ADD CONSTRAINT driver_vehicle_fkey FOREIGN KEY (vehicle) REFERENCES vehicle(id);


--
-- TOC entry 1889 (class 2606 OID 17801)
-- Name: model_brand_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY model
    ADD CONSTRAINT model_brand_fkey FOREIGN KEY (brand) REFERENCES brand(id);


--
-- TOC entry 1890 (class 2606 OID 17814)
-- Name: run_vehicle_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY run
    ADD CONSTRAINT run_vehicle_fkey FOREIGN KEY (vehicle) REFERENCES vehicle(id);


--
-- TOC entry 1886 (class 2606 OID 17831)
-- Name: vehicle_model_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY vehicle
    ADD CONSTRAINT vehicle_model_fkey FOREIGN KEY (model) REFERENCES model(id);


--
-- TOC entry 2004 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2015-06-01 15:19:57

--
-- PostgreSQL database dump complete
--

