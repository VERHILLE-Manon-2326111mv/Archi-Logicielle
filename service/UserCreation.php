<?php

namespace service;

class UserCreation
{
    protected $txt;

    public function getTxt()
    {
        return $this->txt;
    }

    public function createUser($login, $password, $name, $firstName, $data){
        return ($data->createUser($login, $password, $name, $firstName) != false );
    }

}