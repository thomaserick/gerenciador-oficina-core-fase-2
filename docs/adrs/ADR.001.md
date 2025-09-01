# ADR: Estruturação de Pacotes

**Data:** 17/07/2025

## Contexto

A estrutura de pastas do projeto segue uma organização modularizada e com foco no domínio para proporcionar uma
separação clara das responsabilidades, fácil navegação e entendimento rápido do projeto sob uma perspectiva
de negócio.

## Decisão

A estrutura básica foi estabelecida da seguinte forma:

```plaintext
📁core
├── 📁feature-name
│   ├── 📁adapter           
│   │   ├── 📁in                 
│   │   │   ├── 📁api           // Controllers da feature.
│   │   │   │   └── 📁openapi   // Classes com anotações de documentação relacionadas ao swagger das nossas controllers.
│   │   └── 📁out   
│   │       └── 📁db            // Repositórios da feature.
│   ├── 📁app                   // Services responsáveis pela lógica e regra de negócio.
│   ├── 📁domain                // Classes de domínio.
│   │   ├── 📁enums
│   │   └── 📁vo                // Objetos de transferência de informações, normalmente customizadas para casos específicos.
│   ├── 📁usecase               // Interfaces de casos de uso, onde temos as assinaturas dos métodos que serão utilizados nas services.
│   │   └── 📁command           // Objetos com dados necessários para realizar alguma ação. Utilizado para tranferir dados entre controllers, useCases, services, events.
│   ├── 📁exception             // Implementação de exceptions customizadas
│   └──📁sk                     // código compartilhado entre os demais pacotes; deve conter, preferencialmente, identificadores e valores de objetos. 
└── 📁infra                     // código de configuração de recursos técnicos que não fazem parte da implementação de negócio em si, como por exemplo configuração de bibliotecas parceiras.
    ├── 📁controller            // Controllers da feature.    
    │   └── 📁openapi           // Classes com anotações de documentação relacionadas ao swagger das nossas controllers.

```

## Consequências

Em determinados casos pode ser que alguns pacotes fiquem com um número grande de classes. Caso isso venha a acontecer é
prudente agrupar por contexto/domínio para facilitar o entendimento.
