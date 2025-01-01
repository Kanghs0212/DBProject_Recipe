# 레시피 검색 사이트
## 데이터베이스 프로젝트

### 1. 프로젝트 주제
- 다양한 요리를 검색하거나 추천받고, 영양 정보와 레시피를 제공
- 주요 재료는 외부 사이트로 연결해 직접 구매할 수 있도록 지원하는 웹 서비스 구현

&nbsp;&nbsp;

### 2. 주제 선정이유
- 요리에 익숙하지 않은 사람들은 어떤 요리를 만들지 고민하거나, 레시피를 찾아도 재료를 찾아가는 과정이 어려워 쉽게 시도하지 못하는 경우가 있습니다.
- 해당 웹서비스는 사용자가 요리를 검색하거나 추천받고, 자세한 영양 정보와 단계별 레시피를 제공해주며, 재료를 바로 외부 사이트에서 구매할 수 있도록 합니다.
- 이로써 요리에 대한 부담을 낮추고 쉽게 접근할 수 있도록 해줍니다. 


&nbsp;&nbsp;

### 3. DB Design
#### 1) 구체적인 요구사항
1. 사용자는 여러가지 요리를 북마크해놓을수 있다, 하나의 요리는 여러 사용자에게 북마킹될수있다.
2. 요리 테이블에는 하나의 메인재료가 등록되어있다. 
3. 메인재료는 여러 요리 테이블에 등록될수있다.
4. 하나의 메인재료에는 사용자가 구매를 할 수 있게끔 하나의 구매 링크와, 해당 재료를 파는 판매자 이름이 적혀있다.
5. 요리에는 여러 단계의 레시피 단계들이 포함되어있다. 
6. 또한 각 요리에는 카테고리가 있으며 이를 통해 해당 카테고리의 음식을 추천받을 수 있게된다.
7. 각 카테고리는 여러가지의 요리에 해당할수있으며, 요리도 여러가지 카테고리를 가질 수 있다.


&nbsp;&nbsp;
#### 2) ER-다이어그램
![image](https://github.com/user-attachments/assets/a1ce1da7-034a-4dec-b25e-87d8b12e1e3c)


- 위에서 정의한 구체적인 요구사항에 기반하여, ER 다이어그램을 작성해보았다.
1) 사용자(다) - 레시피(다)
- 사이에는 Bookmark 관계로 이어져있으며, 다대다 관계이므로 해당 관계를 테이블화 하여야 한다.
3) 레시피(일) - 레시피단계(다)
- 사이에는 Reference 관계로 이어짐
4) 레시피(다) - 메인재료(일)
- 사이에는 Use 관계로 이어짐
5) 메인재료(일) - 구매링크(일)
- 사이에는 Reference 관계로 이어짐

&nbsp;&nbsp;

#### 3) 데이터베이스 스키마
![image](https://github.com/user-attachments/assets/83ecf6e0-e328-400d-b611-cab369c6ca27)
&nbsp;
![image](https://github.com/user-attachments/assets/3fedf6c8-2045-46f1-b38c-6d03c468bd52)
- 해당 Bookmark 테이블은 사용자와 레시피 다대다 관계 사이에 존재하는 중간 테이블로써, 각 사용자와 레시피의 주키를 복합키이자 외래키로 사용한다.


&nbsp;&nbsp;
![image](https://github.com/user-attachments/assets/0d10b6b4-f2fd-4e06-b483-d1906c2ee302)
&nbsp;

##### 테이블의 정규화 상태
![image](https://github.com/user-attachments/assets/51e19d68-9fe3-4f73-ab9c-4fc89b2607a4)

- 위에서 데이터베이스 스키마를 통해, 모든 테이블들의 속성들이 원자적이며, non-key 속성들이 주키에 완전종속되며, 마지막으로 이행종속이 존재하지 않으므로 전부 제 3정규화를 만족시킨다.
- 여기서 모든 테이블들의 함수 종속(Functional Dependency)를 확인해보면, 모든 결정자들은 후보키(주키)가 되므로 보이스-코드 정규화를 만족한다는 것을 알 수 있다.(BCNF)


