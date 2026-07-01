# ログインフォーム (Login Form)

Java で作られた簡単なログインアプリケーションです。  
Swing を使った GUI 版と、コンソール（Scanner）版の 2 種類があります。

## 機能

- MySQL データベースに接続してユーザー認証
- ユーザー名・パスワードの入力
- ログイン成功 → 「ログインできました！」
- 1 回目の失敗 → 「もう一度ログインしてください！」
- 2 回目の失敗 → 「パスワード忘れ」メッセージ表示

## 必要な環境

- Java 25 以上
- MySQL 8.0 以上
- Maven

## セットアップ手順

### 1. データベースの作成

`src/main/java/loginApp/init.sql` を MySQL で実行してください。

```
mysql -u root -p < src/main/java/loginApp/init.sql
```

これで `loginapp_db` データベースと `users` テーブルが作成され、サンプルユーザーが追加されます。

### 2. MySQL パスワードの設定

`ConsoleApp.java` と `LoginGUI.java` の `DB_PASS` に自分の MySQL パスワードを設定してください。

```java
private static final String DB_PASS = "あなたのパスワード";
```

### 3. 実行方法

**GUI 版:**

```bash
mvn compile exec:java -Dexec.mainClass="loginApp.LoginGUI"
```

または NetBeans で開いて `LoginGUI.java` を実行。

**コンソール版:**

```bash
mvn compile exec:java -Dexec.mainClass="loginApp.ConsoleApp"
```

## サンプルユーザー

| ユーザー名 | パスワード |
|-----------|-----------|
| admin     | pass123   |
| test      | test123   |

## 使っている技術

- **Java Swing** — GUI コンポーネント（JFrame, JTextField, JPasswordField, JButton, JLabel）
- **JDBC** — MySQL データベース接続
- **PreparedStatement** — SQL インジェクション対策
- **Maven** — ビルドツール
