--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: match_status; Type: TYPE; Schema: public; Owner: eriqo.arief1
--

CREATE TYPE public.match_status AS ENUM (
    'Upcoming',
    'Ongoing',
    'Finished'
);


ALTER TYPE public.match_status OWNER TO "eriqo.arief1";

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: fixtures; Type: TABLE; Schema: public; Owner: eriqo.arief1
--

CREATE TABLE public.fixtures (
    id integer NOT NULL,
    home_id integer,
    home_score integer NOT NULL,
    home_odds double precision,
    away_id integer,
    away_score integer NOT NULL,
    away_odds double precision,
    date date NOT NULL,
    status public.match_status NOT NULL,
    gameweek integer NOT NULL
);


ALTER TABLE public.fixtures OWNER TO "eriqo.arief1";

--
-- Name: fixtures_id_seq; Type: SEQUENCE; Schema: public; Owner: eriqo.arief1
--

CREATE SEQUENCE public.fixtures_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fixtures_id_seq OWNER TO "eriqo.arief1";

--
-- Name: fixtures_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eriqo.arief1
--

ALTER SEQUENCE public.fixtures_id_seq OWNED BY public.fixtures.id;


--
-- Name: mini_league_users; Type: TABLE; Schema: public; Owner: eriqo.arief1
--

CREATE TABLE public.mini_league_users (
    id integer NOT NULL,
    mini_league_code character varying(10) NOT NULL,
    user_id integer NOT NULL
);


ALTER TABLE public.mini_league_users OWNER TO "eriqo.arief1";

--
-- Name: mini_league_users_id_seq; Type: SEQUENCE; Schema: public; Owner: eriqo.arief1
--

CREATE SEQUENCE public.mini_league_users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.mini_league_users_id_seq OWNER TO "eriqo.arief1";

--
-- Name: mini_league_users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eriqo.arief1
--

ALTER SEQUENCE public.mini_league_users_id_seq OWNED BY public.mini_league_users.id;


--
-- Name: mini_leagues; Type: TABLE; Schema: public; Owner: eriqo.arief1
--

CREATE TABLE public.mini_leagues (
    code character varying(10) NOT NULL,
    name character varying(50) NOT NULL,
    image text
);


ALTER TABLE public.mini_leagues OWNER TO "eriqo.arief1";

--
-- Name: predictions; Type: TABLE; Schema: public; Owner: eriqo.arief1
--

CREATE TABLE public.predictions (
    id integer NOT NULL,
    user_id integer NOT NULL,
    fixture_id integer NOT NULL,
    pred_home integer NOT NULL,
    pred_away integer NOT NULL,
    points double precision
);


ALTER TABLE public.predictions OWNER TO "eriqo.arief1";

--
-- Name: predictions_id_seq; Type: SEQUENCE; Schema: public; Owner: eriqo.arief1
--

CREATE SEQUENCE public.predictions_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.predictions_id_seq OWNER TO "eriqo.arief1";

--
-- Name: predictions_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eriqo.arief1
--

ALTER SEQUENCE public.predictions_id_seq OWNED BY public.predictions.id;


--
-- Name: refresh_tokens; Type: TABLE; Schema: public; Owner: eriqo.arief1
--

CREATE TABLE public.refresh_tokens (
    id integer NOT NULL,
    token text NOT NULL
);


ALTER TABLE public.refresh_tokens OWNER TO "eriqo.arief1";

--
-- Name: refresh_tokens_id_seq; Type: SEQUENCE; Schema: public; Owner: eriqo.arief1
--

CREATE SEQUENCE public.refresh_tokens_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.refresh_tokens_id_seq OWNER TO "eriqo.arief1";

--
-- Name: refresh_tokens_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eriqo.arief1
--

ALTER SEQUENCE public.refresh_tokens_id_seq OWNED BY public.refresh_tokens.id;


--
-- Name: rewards; Type: TABLE; Schema: public; Owner: eriqo.arief1
--

CREATE TABLE public.rewards (
    id integer NOT NULL,
    name text NOT NULL,
    type character varying(30) NOT NULL,
    image text,
    cost bigint NOT NULL
);


ALTER TABLE public.rewards OWNER TO "eriqo.arief1";

--
-- Name: rewards_id_seq; Type: SEQUENCE; Schema: public; Owner: eriqo.arief1
--

CREATE SEQUENCE public.rewards_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.rewards_id_seq OWNER TO "eriqo.arief1";

--
-- Name: rewards_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eriqo.arief1
--

ALTER SEQUENCE public.rewards_id_seq OWNED BY public.rewards.id;


--
-- Name: teams; Type: TABLE; Schema: public; Owner: eriqo.arief1
--

CREATE TABLE public.teams (
    id integer NOT NULL,
    name character varying(50) NOT NULL,
    crest text NOT NULL,
    odds double precision NOT NULL
);


ALTER TABLE public.teams OWNER TO "eriqo.arief1";

--
-- Name: teams_id_seq; Type: SEQUENCE; Schema: public; Owner: eriqo.arief1
--

CREATE SEQUENCE public.teams_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.teams_id_seq OWNER TO "eriqo.arief1";

--
-- Name: teams_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eriqo.arief1
--

ALTER SEQUENCE public.teams_id_seq OWNED BY public.teams.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: eriqo.arief1
--

CREATE TABLE public.users (
    id integer NOT NULL,
    username character varying(50) NOT NULL,
    email text NOT NULL,
    password character varying(255) NOT NULL,
    predictions_id integer[],
    total_points double precision DEFAULT 0,
    rewards_id integer[],
    mini_league_code character varying(10)
);


ALTER TABLE public.users OWNER TO "eriqo.arief1";

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: eriqo.arief1
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO "eriqo.arief1";

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eriqo.arief1
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: fixtures id; Type: DEFAULT; Schema: public; Owner: eriqo.arief1
--

ALTER TABLE ONLY public.fixtures ALTER COLUMN id SET DEFAULT nextval('public.fixtures_id_seq'::regclass);


--
-- Name: mini_league_users id; Type: DEFAULT; Schema: public; Owner: eriqo.arief1
--

ALTER TABLE ONLY public.mini_league_users ALTER COLUMN id SET DEFAULT nextval('public.mini_league_users_id_seq'::regclass);


--
-- Name: predictions id; Type: DEFAULT; Schema: public; Owner: eriqo.arief1
--

ALTER TABLE ONLY public.predictions ALTER COLUMN id SET DEFAULT nextval('public.predictions_id_seq'::regclass);


--
-- Name: refresh_tokens id; Type: DEFAULT; Schema: public; Owner: eriqo.arief1
--

ALTER TABLE ONLY public.refresh_tokens ALTER COLUMN id SET DEFAULT nextval('public.refresh_tokens_id_seq'::regclass);


--
-- Name: rewards id; Type: DEFAULT; Schema: public; Owner: eriqo.arief1
--

ALTER TABLE ONLY public.rewards ALTER COLUMN id SET DEFAULT nextval('public.rewards_id_seq'::regclass);


--
-- Name: teams id; Type: DEFAULT; Schema: public; Owner: eriqo.arief1
--

ALTER TABLE ONLY public.teams ALTER COLUMN id SET DEFAULT nextval('public.teams_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: eriqo.arief1
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: fixtures; Type: TABLE DATA; Schema: public; Owner: eriqo.arief1
--

COPY public.fixtures (id, home_id, home_score, home_odds, away_id, away_score, away_odds, date, status, gameweek) FROM stdin;
4	1	0	4.2	11	0	0.6	2022-08-13	Upcoming	2
5	3	0	1.1	14	0	3.8	2022-08-13	Upcoming	2
6	6	0	1.1	18	0	1.8	2022-08-14	Upcoming	2
1	9	3	1	12	1	2.1	2022-08-06	Finished	1
2	8	2	0.6	6	1	1.1	2022-08-06	Finished	1
3	14	0	3.8	4	1	1.5	2022-08-07	Finished	1
\.


--
-- Data for Name: mini_league_users; Type: TABLE DATA; Schema: public; Owner: eriqo.arief1
--

COPY public.mini_league_users (id, mini_league_code, user_id) FROM stdin;
1	V8JI0S	3
\.


--
-- Data for Name: mini_leagues; Type: TABLE DATA; Schema: public; Owner: eriqo.arief1
--

COPY public.mini_leagues (code, name, image) FROM stdin;
V8JI0S	Top 100	\N
\.


--
-- Data for Name: predictions; Type: TABLE DATA; Schema: public; Owner: eriqo.arief1
--

COPY public.predictions (id, user_id, fixture_id, pred_home, pred_away, points) FROM stdin;
1	3	1	2	1	10
2	3	2	1	1	20
3	3	3	3	1	30
\.


--
-- Data for Name: refresh_tokens; Type: TABLE DATA; Schema: public; Owner: eriqo.arief1
--

COPY public.refresh_tokens (id, token) FROM stdin;
1	eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywidXNlcm5hbWUiOiJBcmllZiIsImVtYWlsIjoiYXJpZWZAZ21haWwuY29tIiwicGFzc3dvcmQiOiIkMmIkMTAkcmEyMFh5ZlpMS09KWEFlYkY2NXJlT3Z5UjJzSC5Id1VsVnF5Z0hQUFVBcU1xYVduUjZyTEsiLCJwcmVkaWN0aW9uc19pZCI6WzEsMiwzXSwidG90YWxfcG9pbnRzIjo2MCwicmV3YXJkc19pZCI6WzEsMl0sIm1pbmlfbGVhZ3VlX2NvZGUiOiJWOEpJMFMiLCJpYXQiOjE2ODcwNjYwMDd9.OPPkq4sePDe-BbNzrW4SatjrhOMjmDWw1Kg7ay4nV34
15	eyJhbGciOiJIUzI1NiJ9.TmV0bGFi.-dh8UFODJ1VxI1FtX4XOEKJie4axf_O5r4htWjbqA18
17	eyJhbGciOiJIUzI1NiJ9.QXJpZWY.uhhpusWyglmcu5qdNt0zn8ebFyToHMokVYNxvlXg80k
\.


--
-- Data for Name: rewards; Type: TABLE DATA; Schema: public; Owner: eriqo.arief1
--

COPY public.rewards (id, name, type, image, cost) FROM stdin;
1	Google Play $5 Giftcard	Giftcard	https://yt3.googleusercontent.com/UlCw6skRB67meHd_jffAzV6DeXzAk1YzEFyhxI4meSgYAjA0wRhEnhT3TfHvuo7R-VwISzRTTao=s900-c-k-c0x00ffffff-no-rj	999
2	Google Play $10 Giftcard	Giftcard	https://yt3.googleusercontent.com/UlCw6skRB67meHd_jffAzV6DeXzAk1YzEFyhxI4meSgYAjA0wRhEnhT3TfHvuo7R-VwISzRTTao=s900-c-k-c0x00ffffff-no-rj	1999
3	Google Play $15 Giftcard	Giftcard	https://yt3.googleusercontent.com/UlCw6skRB67meHd_jffAzV6DeXzAk1YzEFyhxI4meSgYAjA0wRhEnhT3TfHvuo7R-VwISzRTTao=s900-c-k-c0x00ffffff-no-rj	2999
4	Google Play $20 Giftcard	Giftcard	https://yt3.googleusercontent.com/UlCw6skRB67meHd_jffAzV6DeXzAk1YzEFyhxI4meSgYAjA0wRhEnhT3TfHvuo7R-VwISzRTTao=s900-c-k-c0x00ffffff-no-rj	3999
5	Google Play $100 Giftcard	Giftcard	https://yt3.googleusercontent.com/UlCw6skRB67meHd_jffAzV6DeXzAk1YzEFyhxI4meSgYAjA0wRhEnhT3TfHvuo7R-VwISzRTTao=s900-c-k-c0x00ffffff-no-rj	79999
6	Steam CD Key	Games	https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Steam_icon_logo.svg/512px-Steam_icon_logo.svg.png	11999
7	Gopay IDR 100K	Cash	https://gopay.co.id/icon.png	1299
\.


--
-- Data for Name: teams; Type: TABLE DATA; Schema: public; Owner: eriqo.arief1
--

COPY public.teams (id, name, crest, odds) FROM stdin;
1	Arsenal	https://crests.football-data.org/57.png	4.2
2	Aston Villa	https://crests.football-data.org/58.png	1.3
3	Brentford	https://crests.football-data.org/402.png	1.1
4	Brighton	https://crests.football-data.org/397.svg	1.5
5	Bournemouth	https://crests.football-data.org/1044.png	0.7
6	Chelsea	https://crests.football-data.org/61.png	1.1
7	Crystal Palace	https://crests.football-data.org/354.png	1
8	Everton	https://crests.football-data.org/62.png	0.6
9	Fulham	https://crests.football-data.org/63.svg	1
10	Leeds United	https://crests.football-data.org/341.png	0.5
11	Leicester City	https://crests.football-data.org/338.png	0.6
12	Liverpool	https://crests.football-data.org/64.png	2.1
13	Manchester City	https://crests.football-data.org/65.png	4.5
14	Manchester United	https://crests.football-data.org/66.png	3.8
15	Newcastle Utd	https://crests.football-data.org/67.png	3.5
16	Nottingham Forest	https://crests.football-data.org/351.png	0.7
17	Southampton	https://crests.football-data.org/340.png	0.5
19	West Ham Utd	https://crests.football-data.org/563.png	0.9
20	Wolves	https://crests.football-data.org/76.svg	0.8
18	Tottenham	https://cdn.ssref.net/req/202306092/tlogo/fb/361ca564.png	1.8
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: eriqo.arief1
--

COPY public.users (id, username, email, password, predictions_id, total_points, rewards_id, mini_league_code) FROM stdin;
3	Arief	arief@gmail.com	$2b$10$ra20XyfZLKOJXAebF65reOvyR2sH.HwUlVqygHPPUAqMqaWnR6rLK	{1,2,3}	60	{1,2}	V8JI0S
5	Eriqo1	eriqo1@gmail.com	$2b$10$J2wcPkXa41iIthzhYOG6rexlAxr.0Tm6sEOT49UA07W.ciJDy1jhe	\N	\N	\N	\N
6	Wicaksono	wicaksono@gmail.com	$2b$10$C2HuoRE6A95U9UgX9ibyDum34Gi/F4pPPy4yzC1jkMIWE/N5Oefp.	\N	\N	\N	\N
7	Wicaksono1	wicaksono1@gmail.com	$2b$10$rVKtMHkQOlq178t3pA4nGulPLZHTmp/vqRD7JaKrhKFo8TWFnTSAS	\N	\N	\N	\N
4	Eriqo	eriqo@gmail.com	$2b$10$OitQgv5NlgC3UKUtKNNkP.UZWoQmKfB3fybT7pe7JrmLRz7SbhKB2	\N	1000	\N	\N
10	Netlab	netlab@gmail.com	$2b$10$H.codMpZ2ff9Yn0h23nT0uORFNYQVm0q5j/ih4MUHpt/7LS2SnFLe	\N	0	\N	\N
11	Aloha	aloha@gmail.com	$2b$10$a7ZA.kBzsfZ.cgsojhFTKuYrKUFCQX7lKW/Gs/N.fptHhTDIvc0fC	\N	0	\N	\N
\.


--
-- Name: fixtures_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eriqo.arief1
--

SELECT pg_catalog.setval('public.fixtures_id_seq', 6, true);


--
-- Name: mini_league_users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eriqo.arief1
--

SELECT pg_catalog.setval('public.mini_league_users_id_seq', 1, true);


--
-- Name: predictions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eriqo.arief1
--

SELECT pg_catalog.setval('public.predictions_id_seq', 3, true);


--
-- Name: refresh_tokens_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eriqo.arief1
--

SELECT pg_catalog.setval('public.refresh_tokens_id_seq', 17, true);


--
-- Name: rewards_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eriqo.arief1
--

SELECT pg_catalog.setval('public.rewards_id_seq', 7, true);


--
-- Name: teams_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eriqo.arief1
--

SELECT pg_catalog.setval('public.teams_id_seq', 21, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eriqo.arief1
--

SELECT pg_catalog.setval('public.users_id_seq', 11, true);


--
-- Name: fixtures fixtures_pkey; Type: CONSTRAINT; Schema: public; Owner: eriqo.arief1
--

ALTER TABLE ONLY public.fixtures
    ADD CONSTRAINT fixtures_pkey PRIMARY KEY (id);


--
-- Name: mini_league_users mini_league_users_pkey; Type: CONSTRAINT; Schema: public; Owner: eriqo.arief1
--

ALTER TABLE ONLY public.mini_league_users
    ADD CONSTRAINT mini_league_users_pkey PRIMARY KEY (id);


--
-- Name: mini_leagues mini_leagues_pkey; Type: CONSTRAINT; Schema: public; Owner: eriqo.arief1
--

ALTER TABLE ONLY public.mini_leagues
    ADD CONSTRAINT mini_leagues_pkey PRIMARY KEY (code);


--
-- Name: predictions predictions_pkey; Type: CONSTRAINT; Schema: public; Owner: eriqo.arief1
--

ALTER TABLE ONLY public.predictions
    ADD CONSTRAINT predictions_pkey PRIMARY KEY (id);


--
-- Name: refresh_tokens refresh_tokens_pkey; Type: CONSTRAINT; Schema: public; Owner: eriqo.arief1
--

ALTER TABLE ONLY public.refresh_tokens
    ADD CONSTRAINT refresh_tokens_pkey PRIMARY KEY (id);


--
-- Name: rewards rewards_pkey; Type: CONSTRAINT; Schema: public; Owner: eriqo.arief1
--

ALTER TABLE ONLY public.rewards
    ADD CONSTRAINT rewards_pkey PRIMARY KEY (id);


--
-- Name: teams teams_pkey; Type: CONSTRAINT; Schema: public; Owner: eriqo.arief1
--

ALTER TABLE ONLY public.teams
    ADD CONSTRAINT teams_pkey PRIMARY KEY (id);


--
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: eriqo.arief1
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: eriqo.arief1
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: users users_username_key; Type: CONSTRAINT; Schema: public; Owner: eriqo.arief1
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- Name: fixtures fixtures_away_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: eriqo.arief1
--

ALTER TABLE ONLY public.fixtures
    ADD CONSTRAINT fixtures_away_id_fkey FOREIGN KEY (away_id) REFERENCES public.teams(id) ON DELETE CASCADE;


--
-- Name: fixtures fixtures_home_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: eriqo.arief1
--

ALTER TABLE ONLY public.fixtures
    ADD CONSTRAINT fixtures_home_id_fkey FOREIGN KEY (home_id) REFERENCES public.teams(id) ON DELETE CASCADE;


--
-- Name: users fk_mini_league_code; Type: FK CONSTRAINT; Schema: public; Owner: eriqo.arief1
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk_mini_league_code FOREIGN KEY (mini_league_code) REFERENCES public.mini_leagues(code);


--
-- Name: mini_league_users mini_league_users_mini_league_code_fkey; Type: FK CONSTRAINT; Schema: public; Owner: eriqo.arief1
--

ALTER TABLE ONLY public.mini_league_users
    ADD CONSTRAINT mini_league_users_mini_league_code_fkey FOREIGN KEY (mini_league_code) REFERENCES public.mini_leagues(code) ON DELETE CASCADE;


--
-- Name: mini_league_users mini_league_users_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: eriqo.arief1
--

ALTER TABLE ONLY public.mini_league_users
    ADD CONSTRAINT mini_league_users_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;


--
-- Name: predictions predictions_fixture_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: eriqo.arief1
--

ALTER TABLE ONLY public.predictions
    ADD CONSTRAINT predictions_fixture_id_fkey FOREIGN KEY (fixture_id) REFERENCES public.fixtures(id) ON DELETE CASCADE;


--
-- Name: predictions predictions_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: eriqo.arief1
--

ALTER TABLE ONLY public.predictions
    ADD CONSTRAINT predictions_user_id_fkey FOREIGN KEY (user_id) REFERENCES public.users(id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

