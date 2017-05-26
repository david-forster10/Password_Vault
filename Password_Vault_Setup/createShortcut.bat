@echo off
echo Set oWS = WScript.CreateObject("WScript.Shell") > CreateShortcut.vbs
echo sLinkFile = "C:\ProgramData\Microsoft\Windows\Start Menu\Password_Vault.lnk" >> CreateShortcut.vbs
echo Set oLink = oWS.CreateShortcut(sLinkFile) >> CreateShortcut.vbs
echo oLink.TargetPath = "C:\Users\Admin\AppData\Roaming\Password_Vault\Password_Vault.bat" >> CreateShortcut.vbs
echo oLink.IconLocation = "C:\Users\Admin\AppData\Roaming\Password_Vault\apps\assets_pv1.0\Logo.ico"  >> CreateShortcut.vbs
echo oLink.Save >> CreateShortcut.vbs
cscript CreateShortcut.vbs
del CreateShortcut.vbs
exit
