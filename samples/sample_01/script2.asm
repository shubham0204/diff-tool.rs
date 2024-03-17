section        .text         
global         _start          
_start:
    mov edx, len 
    mov ecx, msg 
    mov ebx, 1
    int 0x80
    mov eax, 1
    int 0x80
section        .data             
    msg        db "Hello world from Assembly!", 0xa
    len        equ $ -msg