# MarkDownNotes

## sublime 中使用markdown

- 所需插件
    1. Markdown Editing // Markdown 编辑和语法高亮支持
    2. Markdown Preview // Markdown 导出 html 预览支持
    3. auto-save // 可自定义的自动保存功能
- 安装步骤
    1. CTRL+SHIFT+P // 打开快速菜单
    2. 输入：pcip(Package Control: Install Pacakge)
    3. 在软件列表内选择需要安装的插件进行安装
- 实时预览
    1. 预览：CTRL+SHIFT+P 打开快速菜单，输入`mp`(Markdown Preview: Preview
       in Browser)
    2. 实时预览  
        2.1 浏览器自动刷新：
            `<meta http-equiv="refresh" content="0.1>"`
            content: 表示刷新间隔  
        2.2 实现 md 文档到 html 文件的自动更新  
            2.2.1 打开auto-save的默认设置和用户设置文件：  
            `Preference->Package  Settings->Auto-save->打开Settings-Defualt和Settings-User`  
            2.2.2 将 Default 的内容复制到 User 内，并修改等待时长：  
            `"auto_save_delay_in_seconds":0.15`  
            2.2.3 进入快速菜单，输入“auto”，设置自动保存范围

<meta http-equiv="refresh" content="1">


