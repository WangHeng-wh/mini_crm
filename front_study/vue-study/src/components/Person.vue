<template>
    <div class="person">
        <h1>姓名：{{name}}</h1>
        <h1>年龄：{{age}}</h1>
    </div>
</template>

<script lang="ts" setup name="Person123">
    let name = '张三'

    let age = 18
</script>

<style scoped>
    .person{
        background-color: aqua;
        box-shadow: 0 0 10px;
        border-radius: 10px;
        padding: 10px;
    }
</style>