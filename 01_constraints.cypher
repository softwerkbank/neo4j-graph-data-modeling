CREATE CONSTRAINT unique_project_name ON (p:Project) ASSERT p.name IS UNIQUE;
CREATE CONSTRAINT unique_certificate_name ON (c:Certificate) ASSERT c.name IS UNIQUE;
CREATE CONSTRAINT unique_knowledge_name ON (k:Knowledge) ASSERT k.name IS UNIQUE;
CREATE CONSTRAINT unique_skill_name ON (s:Skill) ASSERT s.name IS UNIQUE;
CREATE CONSTRAINT unique_company_name ON (c:Company) ASSERT c.name IS UNIQUE;