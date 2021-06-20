# Trip Planner
Spring Boot2で旅程表を共有するWebシステムのサンプルコードです。<br>
ログイン認証（MySQLへのユーザー照合）、データの登録/更新/削除/ダウンロードといった基本的な操作が含まれています。

## Description
Spring Boot2で作ったWebシステムのサンプルコードです。<br>
本サンプルコードは、HTML/CSS、Java、Spring MVC・Spring Data JPA・Spring Securityについて学んだことの実践を目的としています。<br>
そのため、例外処理のハンドリングなど本来実装すべき処理を一部実装していませんので、ご容赦ください。<br>

## Demo


## Dependency
- Java SE Development Kit 11
- Spring Tool Suite 4.10.0.RELEASE
- MySQL 5.7
- Apache Maven 4
- MySQL JDBC Driver 8.0.23
- Spring Boot 2.4.5「Spring MVC（Thymeleaf）、Spring Data JPA、Spring Security」
- Bootstrap 4.5.3
- Lombok 1.18.20

## Setup
1. Spring Tool Suiteで「Springスターター・プロジェクト」を以下のように設定し、作成します。<br>

| 画面 | 項目 | 設定値 |
| ------------- | ------------- | ------------- |
| 新規Spring スターター・プロジェクト | 型| Maven |
| 新規Spring スターター・プロジェクト | Javaバージョン | 11 |
| New Spring Starter Project Dependencies | Spring Boot Version | 2.4.5 |
| New Spring Starter Project Dependencies | 選択済み | Spring Boot Devtools |
| New Spring Starter Project Dependencies | 選択済み | Spring Data JPA |
| New Spring Starter Project Dependencies | 選択済み | Thymeleaf |
| New Spring Starter Project Dependencies | 選択済み | Spring Web |

2. repositoryをcloneします。
3. プロジェクトをクリーンします。
4. application.propertiesをご利用のデータベース情報に変更します。<br>
``/file_sharing/src/main/resources/application.properties``<br>

| 項目 | 設定値 |
| ------------- | ------------- |
| spring.datasource.url | データベースの接続URL |
| spring.datasource.username | データベースの接続ユーザー |
| spring.datasource.password | 接続ユーザーのパスワード |

5. Eclipseの右下のビルド表示が消えたら、プロジェクトが実行できるか、確認します。
5-1. プロジェクト・エクスプローラーから以下のファイルを探します。<br>
``/trip-planner/src/main/java/com.example.demo/TripPlannerApplication.java``<br>
5-2. PortfolioApplication.javaを右クリックし、「実行 > Spring Bootアプリケーション」を実行します。
6. ブラウザから以下のURLにアクセスします。<br>
``http://localhost:8080/trip_planner``
7. 「HOME」ボタンをクリックします。
8. 「新規登録」ボタンをクリックします。
9. ユーザー情報に以下の内容を入力し、「登録」ボタンをクリックします。<br>

| 項目 | 設定値 |
| ------------- | ------------- |
| ユーザーネーム | ユーザー名 |
| パスワード | ユーザーのパスワード |
| メールアドレス | メールアドレス |

## Usage
1. 以下のURLに直接アクセスするか、ログインしていない状態のHOME画面にて「新規作成」または「旅程表」をクリック、または、新規ユーザー登録をすると以下ログインURLへ遷移します。<br>
``http://localhost:8080/trip_planner/login``
2. ユーザー名、パスワードを入力し、ログインします。

## License
This software is released under the MIT License, see LICENSE.

## Authors
Masaaki Ogawa

## Reference
[Spring ドキュメント](https://spring.pleiades.io/)<br>
[Bootstrap5設置ガイド](https://bootstrap-guide.com/outline)<br>
