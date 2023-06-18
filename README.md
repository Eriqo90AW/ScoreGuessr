# Scoreguessr

A Football Scores Guessing App based on Android using NodeJS and PostgreSQL.

---

## Gambaran Umum Program

Sebuah Aplikasi yang memungkinkan pengguna untuk memprediksi hasil dari 3 pertandingan sepak bola terpilih, berbasis Android yang dibuat menggunakan `Android Studio` berbasis Java untuk Front-End dan juga `NodeJS` yang berbasis JavaScript untuk Back-End dan juga Menggunakan Database berbasis `PostgreSQL` yang dibantu dengan layanan Database Serverless yaitu `Neon DB`.

---

## Object Oriented Programming Project for Netlab Assistant Selection

This project is made by myself, a student of computer engineering Universitas Indonesia:

1. [Eriqo Arief Wicaksono](https://github.com/Eriqo90AW)

---

## Postman Documentation Collection

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/24268852-b947e88d-f7e3-4e1b-bfc6-f7fb38a1b957?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D24268852-b947e88d-f7e3-4e1b-bfc6-f7fb38a1b957%26entityType%3Dcollection%26workspaceId%3Da53d3bdf-1a0a-4f30-9f16-d8f36a8229d4)

---

## Penjelasan mengenai Table pada Program

### 1. `Users`

Tabel "users" digunakan untuk menyimpan data pengguna (users) dari aplikasi. Tabel ini memiliki beberapa atribut sebagai berikut:

```
1. "id" (serial, primary key): Atribut ini merupakan identifikasi unik untuk setiap pengguna yang ditambahkan secara otomatis oleh sistem basis data.
2. "username" (varchar(50), unique): Atribut ini digunakan untuk menyimpan nama pengguna (username) pengguna. Nilai di kolom ini harus unik, sehingga tidak ada pengguna lain dengan nama pengguna yang sama.
3. "email" (text, unique): Atribut ini digunakan untuk menyimpan alamat email pengguna. Nilai di kolom ini harus unik, sehingga tidak ada pengguna lain dengan alamat email yang sama.
4. "password" (varchar(255)): Atribut ini digunakan untuk menyimpan kata sandi (password) pengguna dalam bentuk yang dienkripsi.
5. "predictions_id" (int[]): Atribut ini merupakan array (daftar) yang menyimpan ID-ID dari prediksi yang dibuat oleh pengguna.
6. "mini_league_code" (varchar(10)): Atribut ini digunakan untuk menyimpan kode liga mini (mini league) yang terkait dengan pengguna.
7. "total_points" (float): Atribut ini digunakan untuk menyimpan total poin yang diperoleh oleh pengguna dalam aplikasi.
```

### 2. `Teams`

Tabel "teams" digunakan untuk menyimpan data tim (teams). Tabel ini memiliki beberapa atribut sebagai berikut:

```
1. "id" (serial, primary key): Atribut ini merupakan identifikasi unik untuk setiap tim yang ditambahkan secara otomatis oleh sistem basis data.
2. "name" (varchar(50)): Atribut ini digunakan untuk menyimpan nama tim.
3. "crest" (text): Atribut ini digunakan untuk menyimpan URL atau teks yang merepresentasikan lambang tim.
4. "odds" (float): Atribut ini digunakan untuk menyimpan nilai peluang (odds) dari tim.
```

### 3. `Fixtures`

Tabel "fixtures" digunakan untuk menyimpan data pertandingan (fixtures). Tabel ini memiliki beberapa atribut sebagai berikut:

```
1. "id" (serial, primary key): Atribut ini merupakan identifikasi unik untuk setiap pertandingan yang ditambahkan secara otomatis oleh sistem basis data.
2. "home_id" (int): Atribut ini digunakan untuk menyimpan ID tim tuan rumah.
3. "home_score" (int): Atribut ini digunakan untuk menyimpan skor tim tuan rumah.
4. "home_odds" (float): Atribut ini digunakan untuk menyimpan nilai peluang (odds) tim tuan rumah.
5. "away_id" (int): Atribut ini digunakan untuk menyimpan ID tim tamu.
6. "away_score" (int): Atribut ini digunakan untuk menyimpan skor tim tamu.
7. "away_odds" (float): Atribut ini digunakan untuk menyimpan nilai peluang (odds) tim tamu.
8. "date" (date): Atribut ini digunakan untuk menyimpan tanggal pertandingan.
9. "status" (match_status): Atribut ini digunakan untuk menyimpan status pertandingan (misalnya, "ongoing", "finished", dll.).
10. "gameweek" (int): Atribut ini digunakan untuk menyimpan nomor pekan (gameweek) pertandingan.
```

### 4. `Predictions`

Tabel "predictions" digunakan untuk menyimpan data prediksi (predictions). Tabel ini memiliki beberapa atribut sebagai berikut:

```
1. "id" (serial, primary key): Atribut ini merupakan identifikasi unik untuk setiap prediksi yang ditambahkan secara otomatis oleh sistem basis data.
2. "user_id" (int): Atribut ini digunakan untuk menyimpan ID pengguna yang membuat prediksi.
3. "fixture_id" (int): Atribut ini digunakan untuk menyimpan ID pertandingan yang diprediksi.
4. "pred_home" (int): Atribut ini digunakan untuk menyimpan prediksi skor tim tuan rumah.
5. "pred_away" (int): Atribut ini digunakan untuk menyimpan prediksi skor tim tamu.
6. "points" (float): Atribut ini digunakan untuk menyimpan jumlah poin yang diperoleh dari prediksi.
```

### 5. `Mini Leagues`

Tabel "mini_leagues" digunakan untuk menyimpan data liga mini (mini leagues). Tabel ini memiliki beberapa atribut sebagai berikut:

```
1. "code" (varchar(10), primary key): Atribut ini merupakan kode unik untuk setiap liga mini.
2. "name" (varchar(50)): Atribut ini digunakan untuk menyimpan nama liga mini.
3. "image" (text): Atribut ini digunakan untuk menyimpan URL atau teks yang merepresentasikan gambar liga mini.
4. "users_table_id" (int): Atribut ini digunakan untuk menyimpan ID tabel pengguna yang terkait dengan liga mini.
```

### 6. `Mini Leagues Users`

Tabel "mini_league_users" digunakan untuk menyimpan data pengguna liga mini (mini league users). Tabel ini memiliki beberapa atribut sebagai berikut:

```
1. "id" (serial, primary key): Atribut ini merupakan identifikasi unik untuk setiap entri pengguna liga mini yang ditambahkan secara otomatis oleh sistem basis data.
2. "mini_league_code" (varchar(10)): Atribut ini digunakan untuk menyimpan kode liga mini yang terkait dengan pengguna.
3. "user_id" (int): Atribut ini digunakan untuk menyimpan ID pengguna yang terkait dengan liga mini.
```

### 7. `Rewards`

Tabel "rewards" digunakan untuk menyimpan data hadiah (rewards). Tabel ini memiliki beberapa atribut sebagai berikut:

```
1. "id" (serial, primary key): Atribut ini merupakan identifikasi unik untuk setiap hadiah yang ditambahkan secara otomatis oleh sistem basis data.
2. "name" (text): Atribut ini digunakan untuk menyimpan nama hadiah.
3. "type" (varchar(30)): Atribut ini digunakan untuk menyimpan jenis hadiah.
4. "image" (text): Atribut ini digunakan untuk menyimpan URL atau teks yang merepresentasikan gambar hadiah.
5. "cost" (bigint): Atribut ini digunakan untuk menyimpan nilai biaya (cost) hadiah.
```

---

# Tampilan Relation Table dan UML

`Table Relational atau ERD:`

![alt text!](https://github.com/Eriqo90AW/ScoreGuessr/main/Assets/ERD_ScoreGuessr.png)

`UML:`

![alt text!](https://github.com/Eriqo90AW/ScoreGuessr/main/Assets/UML_ScoreGuessr.png)

---

# Tampilan FlowChart

`FlowChart:`

![alt text!](https://github.com/Eriqo90AW/ScoreGuessr/main/Assets/FlowChart_ScoreGuessr.png)

---
