# 類別圖轉 YAML

## 指令目的

將 UML 類別圖（圖片）解析成結構化的 YAML 格式。

**輸入**：一張或多張類別圖（使用者保證都是類別圖）
**輸出**：對應的 YAML 檔案

---

## 知識前情提要

### OOADP 三階段設計方法論

這些類別圖來自「軟體設計模式精通之旅」課程，採用 OOADP（物件導向分析、設計、程式設計）方法論：

1. **OOA（Object-Oriented Analysis）**
   - 將需求結構化，封裝成實體（Entity）、操作（Operation）及行為（Behavior）
   - 產出初步的類別圖模型，呈現系統的靜態結構

2. **OOD（Object-Oriented Design）**
   - 採用 Christopher Alexander 的設計思想（《The Timeless Way of Building》）
   - 設計流程：**Context → Problem（由 Forces 形成）→ Form（Solution）→ Resulting Context**
   - 在 OOA 模型上標註 Forces（設計力量/壓力）
   - 當多個 Forces 產生衝突（Conflicting Forces）時，形成 Problem
   - 只有在「有 Problem」的情況下，才查表選擇對應的設計模式（Solution）來解決
   - **核心原則**：沒有 Conflicting Forces，就不套模式，避免 over design/over engineering

3. **OOP（Object-Oriented Programming）**
   - 依據設計藍圖進行實作

### 類別圖的特殊標記

在這個教學體系中，類別圖會包含以下特殊元素：

- **紅色箭頭（Forces）**：標示設計壓力或問題，指出「這裡有需要解決的設計張力」
- **綠色箭頭（Resolved Forces）**：標示已透過某種設計模式或技術解決的 Forces
- **便條紙（Notes）**：
  - 粉紅色/紅色：通常標示類別的職責、行為說明，或待解決的問題
  - 黃色：補充說明
- **設計模式標記**：明確標示使用的設計模式名稱及參與角色

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

### 擴展元素（Christopher Alexander 設計元素）

**Force（紅色箭頭或標記）**：
- 描述文字：精確描述設計約束或衝突（例如：「某個類型的行為變動性 (BV)」、「擴充性、OCP」、「結構變動性」⋯⋯）
- 起點、終點：如果是箭頭形式
- 附著位置：如果是標記形式
- 顏色標記為 "red"
- **重要性**：Force 是 Christopher Alexander 設計方法的核心，代表設計中的張力與壓力

**Conflicting Forces（衝突的 Forces）**：
- 當兩個或多個 Forces 互相衝突時，形成 Problem
- 例如：「需要彈性擴充」vs「需要保持簡單」
- 記錄哪些 Forces 形成衝突

**Problem（設計問題）**：
- 由 Conflicting Forces 產生的設計問題
- 描述應該精確、可操作，而非空泛
- 這是決定「是否需要套用設計模式」的關鍵

**Resolved Force（綠色箭頭或標記）**：
- 描述文字：說明如何解決 Force 或 Problem
- 通常會註明使用的設計模式或技術（例如：「透過 Proxy Pattern 解決」）
- 起點、終點：如果是箭頭形式
- 顏色標記為 "green"

**Notes（便條紙）**：
- 內容文字：完整轉錄便條內容
- 附著的類別或關係
- 顏色：
  - pink/red：職責說明、行為描述、待解決問題
  - yellow：補充說明、提示
- **重要**：便條紙常用於標示「區分結構與行為」，是評估第二項技能的關鍵

**設計模式（Patterns）**：
- 模式名稱（例如 Proxy, Observer, Factory, Strategy, Template Method）
- 參與的類別列表及其在模式中的角色（例如：ConcreteStrategy, Context, AbstractStrategy）
- 模式解決的 Problem（如果圖上有標示）

---

## YAML 範例結構

```yaml
# 基本 UML 元素
classes:
  - name: "Employee"
    stereotype: null  # 或 "<<entity>>", "<<controller>>" 等
    attributes:
      - name: "id"
        type: "String"
        visibility: "-"
      - name: "name"
        type: "String"
        visibility: "-"
    methods:
      - name: "getId"
        return_type: "String"
        visibility: "+"
        parameters: []

interfaces:
  - name: "IEmployee"
    methods:
      - name: "getId"
        return_type: "String"
        parameters: []

relationships:
  - type: "realization"
    from: "Employee"
    to: "IEmployee"
  - type: "association"
    from: "Company"
    to: "Employee"
    multiplicity:
      from: "1"
      to: "*"
    label: "employs"

# Christopher Alexander 設計元素
forces:
  - description: "需要密碼保護以防止未授權存取"
    from: "Client"
    to: "Database"
    color: "red"
    type: "security"  # 可選：force 的分類
  - description: "需要支援多種資料庫系統"
    attached_to: "DataAccess"
    color: "red"
    type: "flexibility"

conflicting_forces:
  - force_1: "需要保持介面簡單"
    force_2: "需要支援複雜的客製化需求"
    creates_problem: "如何在簡單與彈性之間取得平衡"

problems:
  - description: "如何在不修改客戶端程式碼的情況下，控制對敏感資料的存取"
    caused_by_forces:
      - "需要密碼保護以防止未授權存取"
      - "客戶端不應該知道認證邏輯"
    location: "Client-Database 之間"

resolved_forces:
  - description: "透過 Proxy Pattern 解決存取控制問題"
    resolves_problem: "如何在不修改客戶端程式碼的情況下，控制對敏感資料的存取"
    from: "Proxy"
    to: "RealSubject"
    color: "green"
    pattern_used: "Proxy"

notes:
  - content: "負責管理所有員工資料\n提供 CRUD 操作\n確保資料一致性"
    attached_to: "Database"
    color: "pink"
    note_type: "responsibility"  # responsibility, behavior, todo, clarification
  - content: "使用 Singleton 確保只有一個實例"
    attached_to: "DatabaseConnection"
    color: "yellow"
    note_type: "clarification"

# 設計模式
patterns:
  - name: "Proxy"
    participants:
      - class: "Proxy"
        role: "Proxy"
      - class: "RealSubject"
        role: "RealSubject"
      - class: "ISubject"
        role: "Subject"
    solves_problem: "如何在不修改客戶端程式碼的情況下，控制對敏感資料的存取"
  - name: "Strategy"
    participants:
      - class: "Context"
        role: "Context"
      - class: "ConcreteStrategyA"
        role: "ConcreteStrategy"
      - class: "ConcreteStrategyB"
        role: "ConcreteStrategy"
      - class: "IStrategy"
        role: "Strategy"
```

---

## 解析提示

### 識別 Forces 的線索

- 紅色箭頭或紅色標記
- 常見描述模式：
  - "需要..."、"必須..."、"希望..."
  - "如何..."（問句形式，指出設計張力）
  - "...的壓力"、"...的要求"

### 識別 Problems 的線索

- 通常以問句形式呈現：「如何在...的情況下...？」
- 會連結多個 Forces
- 位於 Forces 附近或便條紙中

### 識別 Resolved Forces 的線索

- 綠色箭頭或綠色標記
- 常見描述模式：
  - "透過...解決"
  - "使用...模式"
  - "藉由...達成"

### 識別設計模式的線索

- 類別名稱包含模式角色：Proxy、Strategy、Observer、Factory、Template、Abstract、Concrete 等
- 圖上明確標註模式名稱
- 特定的結構模式（例如：Context + Strategy + ConcreteStrategy）

### 便條紙顏色的意義

- **粉紅色/紅色**：職責說明、行為描述、待解決的設計問題
- **黃色**：補充說明、提示、注意事項
- **白色/灰色**：一般說明或註解

---

## 執行規則

1. **仔細閱讀圖片**：先整體瀏覽，理解設計脈絡
2. **依序提取元素**：
   - 第一遍：提取所有類別、介面、關係（基本 UML）
   - 第二遍：提取 Forces、Problems、Resolved Forces
   - 第三遍：提取便條紙、設計模式
   - 第四遍：建立連結（Forces → Problem → Solution）
3. **完整性檢查**：確認沒有遺漏任何元素
4. **命名輸出檔案**：使用原檔名 + `_analysis.yaml`
5. **儲存到同目錄**：與原圖片在相同位置

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

### 基本 UML 元素

- [ ] 類別名稱拼寫正確（包括大小寫）
- [ ] 所有屬性完整記錄（名稱、型別、可見性）
- [ ] 所有方法完整記錄（名稱、參數、回傳型別、可見性）
- [ ] 關係類型正確（generalization、realization、association、aggregation、composition、dependency）
- [ ] 基數正確記錄（如果有）
- [ ] Stereotype 正確記錄（如果有）

### Christopher Alexander 設計元素

- [ ] **Forces（紅色標記）**
  - 完整轉錄所有 Force 描述
  - 描述具體、可操作（非空泛）
  - 正確標記位置（from/to 或 attached_to）
  
- [ ] **Conflicting Forces**
  - 識別哪些 Forces 互相衝突
  - 記錄衝突如何產生 Problem
  
- [ ] **Problems（設計問題）**
  - 完整記錄問題描述
  - 連結到產生該問題的 Forces
  - 描述精確、有脈絡（Well-defined Context）
  
- [ ] **Resolved Forces（綠色標記）**
  - 完整轉錄解決方案描述
  - 連結到對應的 Problem
  - 記錄使用的設計模式或技術
  
- [ ] **Notes（便條紙）**
  - 完整轉錄所有便條內容（包含換行）
  - 正確識別顏色（pink/red/yellow）
  - 正確分類 note 類型（responsibility/behavior/todo/clarification）
  - 特別注意粉紅色便條：通常標示職責與行為
  
- [ ] **設計模式**
  - 正確識別模式名稱
  - 列出所有參與類別
  - 標註每個類別在模式中的角色
  - 連結到該模式解決的 Problem（如果圖上有標示）

### YAML 格式

- [ ] YAML 語法正確
- [ ] 縮排一致（使用 2 空格）
- [ ] 所有字串正確引用
- [ ] 列表結構正確

### 整體完整性

- [ ] 沒有遺漏圖片中的任何元素
- [ ] 所有文字都精確轉錄（包括標點符號）
- [ ] 中英文專有名詞正確（如 Proxy、Strategy 等模式名稱）
- [ ] 檔案命名符合規則（原檔名 + `_analysis.yaml`）


