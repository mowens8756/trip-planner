# File Sharing
Spring Boot2でファイルを共有するWebシステムのサンプルコードです。<br>
ログイン認証（MySQLへのユーザー照合）、ファイルの登録/更新/削除/ダウンロードといった基本的な操作が含まれています。

## Description
Spring Boot2で作ったWebシステムのサンプルコードです。<br>
本サンプルコードは、Javaを学び初めた人向けに、Spring MVC・Spring Data JPA・Spring Securityの使い方を伝えることを目的としています。<br>
そのため、例外処理のハンドリングなど本来実装すべき処理を一部実装していませんので、ご容赦ください。<br>

## Demo
[![Image from Gyazo](https://gyazo.com/309bf71e357947d0fba4de8d9670b657.gif)](https://gyazo.com/309bf71e357947d0fba4de8d9670b657)

## Dependency
- Java SE Development Kit 11
- Spring Tool Suite 4.10.0.RELEASE
- MySQL 5.7
- Apache Maven 4
- MySQL JDBC Driver 8.0.23
- Spring Boot 2.4.5「Spring MVC（Thymeleaf）、Spring Data JPA、Spring Security」
- Bootstrap 5.0.0-beta1
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
``/file_sharing/src/main/java/com/portfolio/file/FileSharingApplication.java``<br>
5-2. PortfolioApplication.javaを右クリックし、「実行 > Spring Bootアプリケーション」を実行します。
6. ブラウザから以下のURLにアクセスします。<br>
``http://localhost:8080/file/user_master/index``
7. 「新規作成」ボタンをクリックします。
8. ユーザー情報に以下の内容を入力し、「作成」ボタンをクリックします。<br>

| 項目 | 設定値 |
| ------------- | ------------- |
| ユーザー名 | ユーザー名 |
| 表示名 | ユーザーの表示名 |
| パスワード | ユーザーのパスワード |
| ロール | 管理者 |
| 有効フラグ | 有効 |

## Usage
1. 以下のURLにアクセスし、ログイン画面が表示されます。<br>
``http://localhost:8080/file/login``
2. ユーザー名、パスワードを入力し、ログインします。

## License
This software is released under the MIT License, see LICENSE.

## Authors
Seika.Saito

## Reference
[Spring ドキュメント](https://spring.pleiades.io/)<br>
[Bootstrap5設置ガイド](https://bootstrap-guide.com/outline)<br>
[Login Formレイアウト](https://bootsnipp.com/snippets/z8aQr)
