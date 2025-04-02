<?php

namespace service;

interface DataAccessInterface
{
    public function curlApiToJSON(string $end);
}