variable "project_name" { default = "projeto" }
variable "aws_region" { default = "us-east-1" }


## RDS Envs ##
variable "aws_rds_engine" { default = "postgres" }
variable "aws_rds_engine_version" { default = "16.6" }
variable "aws_rds_instance_class" { default = "db.t4g.micro" }
variable "aws_rds_storage_type" { default = "gp2" }
variable "db_parameter_group_family" { default = "postgres16" }


## VPC Envs ##
variable "vpc_cidr" { default = "10.0.0.0/16" }
