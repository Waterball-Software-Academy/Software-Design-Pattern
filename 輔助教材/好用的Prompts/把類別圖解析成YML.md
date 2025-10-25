# 類別圖轉 YAML

## 指令目的

將 UML 類別圖（圖片）解析成結構化的 YAML 格式。

**輸入**：一張或多張類別圖（使用者保證都是類別圖）
**輸出**：對應的 YAML 檔案

---

## 支援格式

- **輸入**：PNG、JPEG、JPG
- **輸出**：YAML

---

## YAML Schema

你需要提取以下資訊並輸出為 YAML 格式：

### 標準 UML 元素

**類別（Classes）**：
- 名稱（精確拼寫，包含大小寫）
- 屬性：名稱、型別、可見性（+, -, #, ~）
- 方法：名稱、參數、回傳型別、可見性
- Stereotype（如果有）：例如 `<<entity>>`, `<<controller>>`

**介面（Interfaces）**：
- 名稱
- 方法列表

**關係（Relationships）**：
- 類型：generalization（繼承）、realization（實作）、association（關聯）、aggregation（聚合）、composition（組合）、dependency（依賴）
- 起點類別（from）
- 終點類別（to）
- 標籤（label）- 如果有
- 基數（multiplicity）- 如果有，例如 1, 0..1, *, 1..*

### 擴展元素（如果有）

**Force（紅色箭頭）**：
- 描述文字
- 起點、終點
- 顏色標記為 "red"

**Resolved Force（綠色箭頭）**：
- 描述文字
- 起點、終點
- 顏色標記為 "green"

**Notes（便條紙）**：
- 內容文字
- 附著的類別或關係
- 顏色（通常是 pink 或 yellow）

**設計模式（Patterns）**：
- 模式名稱（例如 Proxy, Observer, Factory）
- 參與的類別列表

---

## YAML 範例結構

```yaml
classes:
  - name: "Employee"
    attributes:
      - name: "id"
        type: "String"
        visibility: "-"
    methods:
      - name: "getId"
        return_type: "String"
        visibility: "+"

interfaces:
  - name: "IEmployee"
    methods:
      - name: "getId"
        return_type: "String"

relationships:
  - type: "realization"
    from: "Employee"
    to: "IEmployee"

forces:
  - description: "需要密碼保護以防止未授權存取"
    from: "Client"
    to: "Database"
    color: "red"

resolved_forces:
  - description: "透過 Proxy Pattern 解決"
    from: "Proxy"
    to: "RealSubject"
    color: "green"

notes:
  - content: "負責管理所有員工資料"
    attached_to: "Database"
    color: "pink"

patterns:
  - name: "Proxy"
    participants:
      - "Proxy"
      - "RealSubject"
      - "ISubject"
```

---

## 執行規則

1. **讀取所有圖片**：依序分析每張圖片
2. **提取所有元素**：按照 YAML Schema 完整記錄
3. **命名輸出檔案**：使用原檔名 + `_analysis.yaml`
4. **儲存到同目錄**：與原圖片在相同位置

---

## 使用方式

**單張圖片**：
```
@類別圖轉YAML.md [圖片路徑]
```

**多張圖片**：
```
@類別圖轉YAML.md [圖片1] [圖片2] [圖片3]
```

範例：
```
@類別圖轉YAML.md "diagrams/system.png" "diagrams/design.png"
```

---

## 品質要求

- [ ] 類別名稱拼寫正確（包括大小寫）
- [ ] 所有屬性、方法完整記錄
- [ ] 關係類型正確（繼承、實作、關聯、聚合、組合、依賴）
- [ ] 基數正確記錄（如果有）
- [ ] 紅色箭頭（Force）完整記錄（如果有）
- [ ] 綠色箭頭（Resolved Force）完整記錄（如果有）
- [ ] 便條紙內容完整轉錄（如果有）
- [ ] 設計模式已識別（如果有）
- [ ] YAML 格式正確


